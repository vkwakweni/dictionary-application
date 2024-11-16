package com.mop.dictionaryApp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "senses")
public class Definition implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "synsetid")
    private Integer synsetid;

    @ManyToMany
    @JsonBackReference
    private List<Word> word;

    // Getters and setters
    public Integer getSynsetId() {
        return synsetid;
    }

    public void setSynsetId(Integer synsetid) {
        this.synsetid = synsetid;
    }

    public List<Word> getWord() {
        return this.word;
    }

    public void setWord(List<Word> word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word + ":" + synsetid;
    }
}
