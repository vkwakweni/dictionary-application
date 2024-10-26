


package com.groupproject.controller;

import com.groupproject.model.Word;
import com.groupproject.repository.DictionaryRepository;
import com.groupproject.service.DictionaryService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {

    @Autowired
    private DictionaryService service;
    private final DictionaryRepository repository;

    DictionaryController(DictionaryRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value="/dictionary")
    public ResponseEntity<Word> getWord(Integer id) {
        try {
            Word word = service.getWord(id);
            return new ResponseEntity<Word>(word, HttpStatus.OK); 
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Word>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "word", required = false) String word, Model model) {
        if (word != null && !word.isEmpty()) {
            Word foundWord = service.getDefinition(word);
            model.addAttribute("word", foundWord);
        }
        return "search";
    }
    
}
