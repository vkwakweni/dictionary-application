package com.mop.dictionaryApp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "senses")
@SecondaryTable(name = "synsets", pkJoinColumns = @PrimaryKeyJoinColumn(referencedColumnName = "synsetid", name = "synsetid"))
public class Definition implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "synsetid")
    private Integer synsetid;

    @ManyToMany
    @JoinTable(name = "senses",
                joinColumns = @JoinColumn(name = "synsetid"),
                inverseJoinColumns = @JoinColumn(name = "wordid"))
    @JsonBackReference
    private List<Word> word;

    @Column(table = "synsets", name = "definition")
    private String definition;

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

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return word + ":" + synsetid;
    }
}
