package com.mop.dictionaryApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class WordController {
    @Autowired
    private WordService wordService;

    @PostMapping(value = "/add")
    public void addWord(){
        wordService.addWord();
    }

    @GetMapping("/view")
    public List<Word> getWords() {
        return wordService.getWords();
    }
    
    @GetMapping("/welcome")
    public String welcome(){
        return "Dictionary is a land where words parade and wait for their turn to be noticed.";
    }
}
