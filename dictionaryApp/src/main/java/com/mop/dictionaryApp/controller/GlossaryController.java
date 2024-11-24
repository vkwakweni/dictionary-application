package com.mop.dictionaryApp.controller;

import com.mop.dictionaryApp.exception.ApiException;
import com.mop.dictionaryApp.exception.ErrorCode;
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

    // Create a Glossary for an existing user
    @PostMapping("/{userId}")
    public Glossary createGlossary(@PathVariable Integer userId) {
        Users user = userService.findUserById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return glossaryService.createGlossary(user);
    }

    // Read the Words from a given Glossary
    @GetMapping("/{glossaryId}/words")
public List<String> getWordsInGlossary(@PathVariable Integer glossaryId) {
    // Use orElseThrow to handle missing glossary
    Glossary glossary = glossaryService.findGlossaryById(glossaryId)
            .orElseThrow(() -> new ApiException(ErrorCode.GLOSSARY_NOT_FOUND));

    // Return words or an empty list if no words are available
    return glossary.getWords() != null
            ? glossary.getWords().stream().map(Word::getLemma).toList()
            : List.of();
}

    // Method 1/5
    @GetMapping("/{glossaryId}/sorted-words")
    public List<String> getSortedWords(@PathVariable Integer glossaryId) {
        return glossaryService.getSortedWordsByUserId(glossaryId);
    }

    // Method 2/5
    @GetMapping("/{glossaryId}/word-count")
    public String countWordsInGlossary(@PathVariable Integer glossaryId) {
        glossaryService.findGlossaryById(glossaryId)
                .orElseThrow(() -> new ApiException(ErrorCode.GLOSSARY_NOT_FOUND));
        return glossaryService.countWordsInGlossary(glossaryId);
    }
    
    // Update a Glossary by adding a Word
    @PostMapping("/{glossaryId}/{wordId}")
public Glossary addWordToGlossary(@PathVariable Integer glossaryId, @PathVariable Integer wordId) {
    glossaryService.findGlossaryById(glossaryId)
            .orElseThrow(() -> new ApiException(ErrorCode.GLOSSARY_NOT_FOUND));

    Word word = wordRepository.findWordWithDefinitionsById(wordId)
            .orElseThrow(() -> new ApiException(ErrorCode.WORD_NOT_FOUND));
    return glossaryService.addWordToGlossary(glossaryId, word);
}


    // Delete a Word from a Glossary
    @DeleteMapping("/{glossaryId}/words/{wordId}")
public Glossary removeWordFromGlossary(@PathVariable Integer glossaryId, @PathVariable Integer wordId) {
    glossaryService.findGlossaryById(glossaryId)
            .orElseThrow(() -> new ApiException(ErrorCode.GLOSSARY_NOT_FOUND));

    return glossaryService.removeWordFromGlossary(glossaryId, wordId);
}


    @DeleteMapping("/{glossaryId}")
    public ResponseEntity<Void> deleteGlossary(@PathVariable Integer glossaryId) {
        glossaryService.findGlossaryById(glossaryId)
                .orElseThrow(() -> new ApiException(ErrorCode.GLOSSARY_NOT_FOUND));
    
        glossaryService.deleteGlossary(glossaryId);
        return ResponseEntity.noContent().build();
    }
    

}
