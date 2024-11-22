package com.mop.dictionaryApp.controller;

import com.mop.dictionaryApp.model.Glossary;
import com.mop.dictionaryApp.model.Users;
import com.mop.dictionaryApp.model.Word;
import com.mop.dictionaryApp.repository.WordRepository;
import com.mop.dictionaryApp.service.GlossaryService;
import com.mop.dictionaryApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/glossaries")
public class GlossaryController {

    @Autowired
    private GlossaryService glossaryService;
    
    @Autowired
    private UsersService userService;

    @Autowired
    private WordRepository wordRepository;

    @PostMapping("/{userId}")
    public Glossary createGlossary(@PathVariable Integer userId) {
        Users user = userService.findUserById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return glossaryService.createGlossary(user);
    }

    @PostMapping("/{glossaryId}/{wordid}")
    public Glossary addWordToGlossary(@PathVariable Integer glossaryId, @PathVariable Integer wordid) {
        Word word = wordRepository.findWordWithDefinitionsById(wordid)
        .orElseThrow(() -> new RuntimeException("Word not found"));
        return glossaryService.addWordToGlossary(glossaryId, word);
        // Optional<Word> word = wordRepository.findWordWithDefinitionsById(wordid);
        // return glossaryService.addWordToGlossary(glossaryId, word.map(w -> w).orElseGet(null));
    }

    @DeleteMapping("/{glossaryId}/words/{wordId}")
    public Glossary removeWordFromGlossary(@PathVariable Integer glossaryId, @PathVariable Integer wordId) {
        return glossaryService.removeWordFromGlossary(glossaryId, wordId);
    }

    @DeleteMapping("/{glossaryId}")
    public ResponseEntity<Void> deleteGlossary(@PathVariable Integer glossaryId) {
        glossaryService.deleteGlossary(glossaryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{glossaryId}/words")
    public List<String> getWordsInGlossary(@PathVariable Integer glossaryId) {
        Glossary glossary = glossaryService.findGlossaryById(glossaryId)
            .orElseThrow(() -> new RuntimeException("Glossary not found"));
        
        return glossary.getWords().stream().map(Word::getLemma).toList();
    }
    @GetMapping("/{userId}/sorted-words")
    public List<String> getSortedWords(@PathVariable Integer userId) {
        return glossaryService.getSortedWordsByUserId(userId);
    }

    @GetMapping("/{glossaryId}/word-count")
    public String countWordsInGlossary(@PathVariable Integer glossaryId) {
        return glossaryService.countWordsInGlossary(glossaryId);
    }
}
