package com.mop.dictionaryApp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "senses")
@NamedQueries({
    @NamedQuery(name = "Word.findAll", query = "SELECT distinct w FROM Word w"),
    @NamedQuery(name = "Word.findByWordId", query = "SELECT distinct w FROM Word w WHERE w.id = :wordid")
})
public class Word implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "wordid")
    private Integer wordid;

    // @Query(value = "Select w.synsetid from Word w where w.wordid = :wordid")

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "senses",
                joinColumns = @JoinColumn(name = "wordid"),
                inverseJoinColumns = @JoinColumn(name = "synsetid"))
    @JsonManagedReference
    private List<Definition> synsets = new ArrayList<Definition>() ;

    public Word() {}
    //     this.wordid = word;
    //     this.synsetSet = new ArrayList<>();
    // }

    public Word(Integer wordid) {
        this.wordid = wordid;
    }

    public Integer getWordId() {
        return this.wordid;
    }

    public void setWordId(Integer id) {
        this.wordid = id;
    }

    public List<Definition> getSynsets() {
        return this.synsets;
    }

    public List<Definition> setSynsets(List<Definition> synsets) {
        return this.synsets = synsets;
    }
   
    @Override
    public String toString() {
        return this.wordid + ": " + this.synsets;
    }
}
