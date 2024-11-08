package com.mop.dictionaryApp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "senses")
@SecondaryTable(name = "synsets", pkJoinColumns = @PrimaryKeyJoinColumn(name = "synsetid"))
@SecondaryTable(name = "words", pkJoinColumns = @PrimaryKeyJoinColumn(name = "wordid"))
public class Word implements Serializable {
    @Id
    @Column(name = "wordid")
    private Integer wordID;
    @Column(name = "synsetid")
    private Integer definitionID;
    @Column(name = "lemma")
    private String word;

    public Word() {}

    public Word(Integer wordId) {
        this.wordID = wordId;
        // and then SQL calls to fill in the rest.
    }

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

    public String getWord(Integer id){
        return word;
    }

    public void setWord(String word){
        this.word = word;
    }

    @Override
    public String toString() {
        return wordID + ": " + word;
    }

}
