package com.mop.dictionaryApp.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "senses")
public class Definition {

    @Id
    @Column(name = "synsetid")
    private Integer synsetid;

    @ManyToOne
    @JoinColumn(name = "wordid")
    private Word word;

    // Getters and setters
    public Integer getSynsetId() {
        return synsetid;
    }

    public void setSynsetId(Integer synsetid) {
        this.synsetid = synsetid;
    }

    public Word getWord() {
        return this.word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word.getWordId() + ":" + synsetid;
    }
}
