package com.mop.dictionaryApp.service;

import com.mop.dictionaryApp.model.Glossary;
import com.mop.dictionaryApp.model.Users;
import com.mop.dictionaryApp.model.Word;
import com.mop.dictionaryApp.repository.GlossaryRepository;
import com.mop.dictionaryApp.repository.UsersRepository;
import com.mop.dictionaryApp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Service
public class GlossaryService {

    @Autowired
    private final GlossaryRepository glossaryRepository;

    @Autowired
    private UsersRepository usersRepository;

    @PersistenceContext
    private EntityManager em;

    // @Autowired
    // private WordRepository wordRepository;

    public GlossaryService(GlossaryRepository glossaryRepository){
        this.glossaryRepository = glossaryRepository;
    }

    public Glossary createGlossary(Users user) {
        Glossary glossary = new Glossary();
        glossary.setUser(user);
        return glossaryRepository.save(glossary);
    }

    public Optional<Glossary> findGlossaryById(Integer id) {
        return glossaryRepository.findById(id);
    }

    public Glossary addWordToGlossary(Integer glossaryId, Word word) {
        Optional<Glossary> glossaryOpt = glossaryRepository.findById(glossaryId);
        if (glossaryOpt.isPresent()) {
            Glossary glossary = glossaryOpt.get();
            glossary.getWords().add(word);
            return glossaryRepository.save(glossary);
        }
        throw new RuntimeException("Glossary not found.");
    }

    public Glossary removeWordFromGlossary(Integer glossaryId, Integer wordId) {
        Optional<Glossary> glossaryOpt = glossaryRepository.findById(glossaryId);
        if (glossaryOpt.isPresent()) {
            Glossary glossary = glossaryOpt.get();
            glossary.getWords().removeIf(word -> word.getWordId().equals(wordId));
            return glossaryRepository.save(glossary);
        }
        throw new RuntimeException("Glossary not found.");
    }
    
    public List<String> getSortedWordsByUserId(Integer userId) {
        // Fetch user and associated glossary
        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Glossary glossary = glossaryRepository.findByUserId(user.getId())
            .orElseThrow(() -> new RuntimeException("Glossary not found for user"));

        // Get words and sort alphabetically by lemma
        return glossary.getWords().stream()
            .map(Word::getLemma) // Extract word lemma
            .sorted()           // Sort alphabetically
            .toList();          // Collect into a list
    }
}