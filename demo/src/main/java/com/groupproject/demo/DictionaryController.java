


package com.groupproject.controller;

import com.groupproject.model.Word;
import com.groupproject.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {
      @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/search")
    public String search(@RequestParam(value = "word", required = false) String word, Model model) {
        if (word != null && !word.isEmpty()) {
            Word foundWord = dictionaryService.getDefinition(word);
            model.addAttribute("word", foundWord);
        }
        return "search";
    }
    
}
