package com.mop.dictionaryApp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    

    @GetMapping("/{wordid:[0-9]+}/synonyms")
    public ResponseEntity<Set<Word>> getSynonyms(@PathVariable Integer wordid) {
        Set<Word> synonyms = wordService.getSynonymsOfWord(wordid);
        return ResponseEntity.ok(synonyms);
    }


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


    //word doesnt exist

    @GetMapping("/search/pattern")
    public List<String> searchWords(@RequestParam String pattern) {
        return wordService.searchWordsByPattern(pattern);
    }

    @GetMapping("/search/{lemma}")
    public List<String> searchWord(@PathVariable String lemma) {
        return wordService.searchWordByLemma(lemma);
    }

    // Method 6/5 - Redirect to Wikipedia for word
    @GetMapping("/redirect/{lemma}")
    public String redirect(@PathVariable String lemma) {
        ResponseEntity.status(HttpStatus.FOUND)
         .location(URI.create("https://en.wikipedia.org/wiki/" + lemma))
        .build();
        String redirect = "<p><a href=\"https://en.wikipedia.org/wiki/" + lemma + "\">Redirect</a></p>";
        return  redirect;
    }

    // Method 5/5 - Check if two words are anagrams
    @GetMapping("/search/anagram/{lemma1}-{lemma2}")
    public boolean checkIfAnagram(@PathVariable String lemma1, @PathVariable String lemma2) {
        return wordService.checkIfAnagram(lemma1, lemma2);
    }

    
}
