
package com.groupproject.service;

import com.groupproject.model.Word;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;


@Service
public class DictionaryService {
    
     private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "A greeting or expression of goodwill.");
        dictionary.put("world", "The earth, together with all of its countries, peoples, and natural features.");
        // Add more words and definitions
    }

    public Word getDefinition(String word) {
        String definition = dictionary.get(word.toLowerCase());
        if (definition != null) {
            return new Word(word, definition);
        } else {
            return new Word(word, "Definition not found.");
        }
    }
    
}
