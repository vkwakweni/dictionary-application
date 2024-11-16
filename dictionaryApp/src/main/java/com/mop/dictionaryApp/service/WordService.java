package com.mop.dictionaryApp.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
    public Word getWordWithDefinitionsById(Integer wordid) {
        return wordRepository.findWordWithDefinitionsById(wordid);
    }

    // Fetch all words with their definitions
    @Transactional
    public List<Word> getAllWordsWithDefinitions() {
        return wordRepository.findAllWordsWithDefinitions();
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
