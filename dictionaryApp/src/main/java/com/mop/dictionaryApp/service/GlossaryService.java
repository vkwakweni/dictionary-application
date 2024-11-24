package com.mop.dictionaryApp.service;

import com.mop.dictionaryApp.model.Glossary;
import com.mop.dictionaryApp.model.Users;
import com.mop.dictionaryApp.model.Word;
import com.mop.dictionaryApp.repository.GlossaryRepository;
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

    @PersistenceContext
    private EntityManager em;

    public GlossaryService(GlossaryRepository glossaryRepository){
        this.glossaryRepository = glossaryRepository;
    }

    // Creating a glossary from an existing Users object
    // TODO: Restrict creation to if User does NOT have a Glossary
    public Glossary createGlossary(Users user) {
        Glossary glossary = new Glossary();
        glossary.setUser(user);
        return glossaryRepository.save(glossary);
    }

    // Getting a Glossary objectby ID (Read)
    public Optional<Glossary> findGlossaryById(Integer id) {
        return glossaryRepository.findById(id);
    }

    // Updating a Glossary with an existing Word object
    // TODO: should do try-catch block for existence of glossary and word.
    // TODO: Have Not Found exception for each model
    public Glossary addWordToGlossary(Integer glossaryId, Word word) {
        Optional<Glossary> glossaryOpt = glossaryRepository.findById(glossaryId);
        if (glossaryOpt.isPresent()) {
            Glossary glossary = glossaryOpt.get();
            if (!glossary.getWords().contains(word)) {
            glossary.getWords().add(word);
            }
            return glossaryRepository.save(glossary);
        }
        throw new RuntimeException("Glossary not found for given id: " + glossaryId);
    }

    // Deleting a Word from a Glossary
    public Glossary removeWordFromGlossary(Integer glossaryId, Integer wordId) {
        Optional<Glossary> glossaryOpt = glossaryRepository.findById(glossaryId);
        if (glossaryOpt.isPresent()) {
            Glossary glossary = glossaryOpt.get();
            glossary.getWords().removeIf(word -> word.getWordId().equals(wordId));
            return glossaryRepository.save(glossary);
        }
        throw new RuntimeException("Glossary not found with id: " + glossaryId);
    }

    // Deleting a Glossary
    public void deleteGlossary(Integer glossaryId) {
        if (glossaryRepository.existsById(glossaryId)) {
            glossaryRepository.deleteById(glossaryId);
        } else {
            throw new RuntimeException("Glossary not found with id: " + glossaryId);
        }
    }
    
    // Method 1/5: Sorting Words in a Glossary
    // TODO: Displaying words should also lead from glossary.
    public List<String> getSortedWordsByUserId(Integer glossaryId) {
        Glossary glossary = glossaryRepository.findById(glossaryId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Glossary glossary = glossaryRepository.findByUserId(user.getId())
        //     .orElseThrow(() -> new RuntimeException("Glossary not found for user"));

        return glossary.getWords().stream().map(Word::getLemma).sorted().toList();          
    }

    // Method 2/5: Counting Words in a Glossary
    public String countWordsInGlossary(Integer glossaryId) {
        Glossary glossary = glossaryRepository.findById(glossaryId)
            .orElseThrow(() -> new RuntimeException("Glossary not found"));

        int count = glossary.getWords().size();

        return count + " words in this glossary";
    }
}