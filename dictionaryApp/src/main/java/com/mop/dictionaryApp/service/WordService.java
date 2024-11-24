package com.mop.dictionaryApp.service;

import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mop.dictionaryApp.exception.ApiException;
import com.mop.dictionaryApp.exception.ErrorCode;
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

    public Word createWord(Word word) { // maybe save word instead
        return wordRepository.save(word);
    }

    // Read Word by giving a Word Integer
    @Transactional
    public Optional<Word> getWordWithDefinitionsById(Integer wordid) {
        return wordRepository.findWordWithDefinitionsById(wordid);
    }

    // Not the same as the above?
    public Optional<Word> getWordById(Integer word) {
        return wordRepository.findById(word);
    }

    // Method 3/5 - Get Synonyms of a Word
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


    // Delete a Word by ID
    public void deleteWord(Integer word) {
        if (wordRepository.existsById(word)) {
            wordRepository.deleteById(word);
        } else {
            throw new ApiException(ErrorCode.WORD_NOT_FOUND);
        }
    }

    // Method 4/5 - Search by Pattern
    public List<String> searchWordsByPattern(String pattern) {
        // Use Pageable to limit results to 10
        Pageable limit = PageRequest.of(0, 10);
        return wordRepository.findWordsByPattern(pattern, limit);
    }

    // Method 4/5 - Search by Whole Word
    public List<String> searchWordByLemma(String lemma) {
        Integer wordid = wordRepository.findWordByLemma(lemma);
        Optional<Word> wordOptional = wordRepository.findWordWithDefinitionsById(wordid);
        Word word = wordOptional.map(w -> w).orElseGet(null);
        return word.getSynsets().stream().map(Definition::getDefinition).toList();
    }

    // Method 5/5 - Get Anagrams of a word
    public boolean checkIfAnagram(String lemma1, String lemma2) {
        char[] letters1 = lemma1.toCharArray();
        char[] letters2 = lemma2.toCharArray();
        Arrays.sort(letters1); 
        Arrays.sort(letters2);
        return Arrays.equals(letters1, letters2);
        
    }

}
