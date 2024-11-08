package com.mop.dictionaryApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class WordController {
    @Autowired
    private WordService wordService;

    @GetMapping("/words")
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable Integer id) {
        Optional<Word> word = wordService.getWordById(id);
        return word.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Word createWord(@RequestBody Word word) {
        return wordService.createWord(word);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Integer id) {
        wordService.deleteWord(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/welcome")
    public String welcome(){
        return "Dictionary is a land where words parade and wait for their turn to be noticed.";
    }
}
