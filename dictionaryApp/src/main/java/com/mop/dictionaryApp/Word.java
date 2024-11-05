package com.mop.dictionaryApp;

import java.util.List;

public class Word {
    private Integer wordID;
    private Integer definitionID;
    private String word;
    private List<Word> synonyms;

    public Integer getWordId(){
        return wordID;
    }
    
    public void setWordId(Integer id){
        this.wordID = id;
    }

    public Integer getDefinitionId(){
        return definitionID;
    }
    
    public void setDefinitionId(Integer id){
        this.definitionID = id;
    }

    public String getWord(){
        return word;
    }
    
    public void setWord(String word){
        this.word = word;
    }

    public List<Word> getSynonyms(){
        return synonyms;
    }
    
    public void setSynonyms(List<Word> synonyms){
        this.synonyms = synonyms;
    }

}
