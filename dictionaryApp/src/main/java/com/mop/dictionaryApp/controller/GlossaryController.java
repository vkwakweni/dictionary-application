package com.mop.dictionaryApp.controller;

import com.mop.dictionaryApp.model.Glossary;
import com.mop.dictionaryApp.model.Users;
import com.mop.dictionaryApp.model.Word;
import com.mop.dictionaryApp.repository.WordRepository;
import com.mop.dictionaryApp.service.GlossaryService;
import com.mop.dictionaryApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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
        Optional<Word> word = wordRepository.findWordWithDefinitionsById(wordid);
        return glossaryService.addWordToGlossary(glossaryId, word.map(w -> w).orElseGet(null));
    }

    @DeleteMapping("/{glossaryId}/words/{wordId}")
    public Glossary removeWordFromGlossary(@PathVariable Integer glossaryId, @PathVariable Integer wordId) {
        return glossaryService.removeWordFromGlossary(glossaryId, wordId);
    }

    @GetMapping("/{glossaryId}/words")
    public List<Word> getWordsInGlossary(@PathVariable Integer glossaryId) {
        Glossary glossary = glossaryService.findGlossaryById(glossaryId)
            .orElseThrow(() -> new RuntimeException("Glossary not found"));
        return glossary.getWords();
    }
}
