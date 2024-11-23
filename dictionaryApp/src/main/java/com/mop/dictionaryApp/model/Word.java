package com.mop.dictionaryApp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "senses")
@SecondaryTable(name = "words", pkJoinColumns = @PrimaryKeyJoinColumn(referencedColumnName = "wordid", name = "wordid"))
@NamedQueries({
    @NamedQuery(name = "Word.findAll", query = "SELECT distinct w FROM Word w"), // tODO: are these usefull?
    @NamedQuery(name = "Word.findByWordId", query = "SELECT distinct w FROM Word w WHERE w.id = :wordid")
})
public class Word implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "wordid")
    private Integer wordid;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "senses",
                joinColumns = @JoinColumn(name = "wordid"),
                inverseJoinColumns = @JoinColumn(name = "synsetid"))
    @JsonManagedReference
    private List<Definition> synsets;

    @Column(table = "words", name = "lemma", insertable = false, updatable = false)
    private String lemma;

    public Word() {}

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
        Set<Definition> defs = new HashSet<Definition>(this.synsets);
        this.synsets.clear();
        this.synsets.addAll(defs);
        return this.synsets;
    }

    public void setSynsets(List<Definition> synsets) {
        Set<Definition> defs = new HashSet<Definition>(synsets);
        this.synsets.clear();
        this.synsets.addAll(defs);
    }

    public String getLemma() {
        return this.lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    // TODO: better string representation
    @Override
    public String toString() {
        return this.wordid + ": " + this.synsets;
    }
}
