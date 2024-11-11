package com.mop.dictionaryApp;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class WordService {

    private final WordRepository wordRepository;

    @PersistenceContext
    private EntityManager em;

    public WordService(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWordById(Word word) {
        return wordRepository.findById(word.getWordId());
    }

    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    public void deleteWord(Word word) {
        if (wordRepository.existsById(word.getWordId())) {
            wordRepository.deleteById(word.getWordId());
        } else {
            throw new RuntimeException("Word not found with id: " + word.getWordId());
        }
    }

}
