package com.mop.dictionaryApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mop.dictionaryApp.model.Word;
import com.mop.dictionaryApp.service.WordService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "Word API", description = "Operations related to words and definitions")
public class WordController {
    @Autowired
    private WordService wordService;
    
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ApiOperation(value = "Fetch a word by ID", response = Word.class)
    @GetMapping("/{wordid:[0-9]+}")
    public ResponseEntity<Word> getWordWithDefinitions( 
        @ApiParam(value = "ID of the word", required = true) @PathVariable Integer wordid){
            Optional<Word> word = wordService.getWordWithDefinitionsById(wordid);
            return word.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        
    @ApiOperation(value = "Fetch all words with definitions")
    @GetMapping("/words")
    public List<Word> getAllWordsWithDefinitions() {
        return wordService.getAllWordsWithDefinitions();
    }

    // @GetMapping("/words")
    // public List<Word> getAllWords() {
    //     return wordService.getAllWords();
    // }

    // @GetMapping("/{wordid}")
    // public ResponseEntity<Word> getWordById(@PathVariable Integer wordid) {
    //     Optional<Word> word = wordService.getWordById(wordid);
    //     return word.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    // @GetMapping("/{wordId}")
    // public ResponseEntity<Word> getByWordId(@PathVariable Integer wordId) {
    //     Word search = new Word(wordId);
    //     Optional<Word> word = wordService.getWordById(search);
    //     return word.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    @PostMapping
    public Word createWord(@RequestBody Word word) {
        return wordService.createWord(word);
    }

    @DeleteMapping("/{wordid:[0-9]+}")
    public ResponseEntity<Void> deleteWord(@PathVariable Integer wordid) {
        wordService.deleteWord(wordid);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/welcome")
    public String welcome(){
        return "Dictionary is a land where words parade and wait for their turn to be noticed.";
    }
}
