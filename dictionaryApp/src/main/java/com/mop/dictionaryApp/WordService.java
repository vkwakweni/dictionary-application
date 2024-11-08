package com.mop.dictionaryApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWordById(Integer id) {
        return wordRepository.findById(id);
    }

    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    public void deleteWord(Integer id) {
        if (wordRepository.existsById(id)) {
            wordRepository.deleteById(id);
        } else {
            throw new RuntimeException("Word not found with id: " + id);
        }
    }

}
