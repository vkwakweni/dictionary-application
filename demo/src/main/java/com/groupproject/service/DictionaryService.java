
package com.groupproject.service;

import com.groupproject.model.Word;
import com.groupproject.repository.DictionaryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DictionaryService {

    @Autowired
    private DictionaryRepository repository;

    @PersistenceContext
    private EntityManager em;
    
    public Word getWord(Integer id){
        return repository.getById(id);
    }

    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "A greeting or expression of goodwill.");
        dictionary.put("world", "The earth, together with all of its countries, peoples, and natural features.");
        // Add more words and definitions
    }

    public Word getDefinition(String word) {
        ArrayList<String> definition = new ArrayList<String>();
        definition.add(dictionary.get(word.toLowerCase()));
        return new Word(word, definition);
        }
    }
    


