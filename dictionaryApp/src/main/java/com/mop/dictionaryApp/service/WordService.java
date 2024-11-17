package com.mop.dictionaryApp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mop.dictionaryApp.model.Definition;
import com.mop.dictionaryApp.model.Word;
import com.mop.dictionaryApp.repository.WordRepository;

@Transactional
@Service
public class WordService {

    private final WordRepository wordRepository;

    @PersistenceContext
    private EntityManager em;

    public WordService(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

    @Transactional
    public Optional<Word> getWordWithDefinitionsById(Integer wordid) {
        return wordRepository.findWordWithDefinitionsById(wordid);
    }

    // Fetch all words with their definitions
    @Transactional
    public List<Word> getAllWordsWithDefinitions() {
        return wordRepository.findAllWordsWithDefinitions();
    }

    @Transactional
    public Set<Word> getSynonymsOfWord(Integer wordid) {
        Optional<Word> word = wordRepository.findWordWithDefinitionsById(wordid);
        List<Definition> definitions = word.map(w -> w.getSynsets()).orElseGet(null);
        Set<Word> result = new HashSet<>();
        for (int i = 0; i < definitions.size(); i++) {
            List<Word> words = definitions.get(i).getWord();
            for (int j = 0; j < words.size(); j++) {
                if (words.get(j) != word.map(w -> w).orElseGet(null)) {
                result.add(words.get(j));
                }
            }
        }
        return result;
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWordById(Integer word) {
        return wordRepository.findById(word);
    }

    public Word createWord(Word word) { // maybe save word instead
        return wordRepository.save(word);
    }

    public void deleteWord(Integer word) {
        if (wordRepository.existsById(word)) {
            wordRepository.deleteById(word);
        } else {
            throw new RuntimeException("Word not found with id: " + word);
        }
    }

}
