package com.mop.dictionaryApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class WordService {
    private List<Word> dictionary = new ArrayList<>();
    private List<Word> notYet = new ArrayList<>();

    public void addWord(){
        Word love = new Word();
        love.setWordId(79459);
        love.setDefinitionId(107558676);
        love.setWord("love");
        love.setSynonyms(notYet);
        dictionary.add(love);
    }

    public List<Word> getWords() {
        return dictionary;
    }
}
