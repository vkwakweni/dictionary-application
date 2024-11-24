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

    // READ - Search for a word that begin with pattern
    @GetMapping("/search/pattern")
    public List<String> searchWords(@RequestParam String pattern) {
        return wordService.searchWordsByPattern(pattern);
    }

    // READ - Search for an exact word - READ
    @GetMapping("/search/{lemma}")
    public List<String> searchWord(@PathVariable String lemma) {
        return wordService.searchWordByLemma(lemma);
    }

    // READ -Get a word by ID 
    @ApiOperation(value = "Fetch a word by ID", response = Word.class)
    @GetMapping("/{wordid:[0-9]+}")
    public ResponseEntity<Word> getWordWithDefinitions( 
        @ApiParam(value = "ID of the word", required = true) @PathVariable Integer wordid){
            Optional<Word> word = wordService.getWordWithDefinitionsById(wordid);
            return word.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
    
    // Method - Get the synonym of a word
    @GetMapping("/{lemma}/synonyms")
    public ResponseEntity<Set<Word>> getSynonyms(@PathVariable String lemma) {
        Set<Word> synonyms = wordService.getSynonymsOfWord(lemma);
        return ResponseEntity.ok(synonyms);
    }
    
    @GetMapping("/welcome")
    public String welcome(){
        return "Dictionary is a land where words parade and wait for their turn to be noticed.";
    }

    // Method - Redirect to HTML page for a redirect to Wikipedia article
    @GetMapping("/redirect/{lemma}")
    public String redirect(@PathVariable String lemma) {
        ResponseEntity.status(HttpStatus.FOUND)
         .location(URI.create("https://en.wikipedia.org/wiki/" + lemma))
        .build();
        String redirect = "<p><a href=\"https://en.wikipedia.org/wiki/" + lemma + "\">Redirect to Wikipedia</a></p>";
        return  redirect;
    }

    // Method - Check if two words are anagrams
    @GetMapping("/search/anagram/{lemma1}-{lemma2}")
    public boolean checkIfAnagram(@PathVariable String lemma1, @PathVariable String lemma2) {
        return wordService.checkIfAnagram(lemma1, lemma2);
    }

    
}
