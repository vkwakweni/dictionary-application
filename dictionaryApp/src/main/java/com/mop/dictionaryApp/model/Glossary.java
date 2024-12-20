package com.mop.dictionaryApp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Glossary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // table glossary that has a column user_id
    private Users user;

    @ManyToMany
    @JoinTable(
        name = "glossary_words",  // to update a foreign key between glossary to glossary_words
        joinColumns = @JoinColumn(name = "glossary_id"),
        inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private List<Word> words;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        if (user.getGlossaries() == null) {
            this.user = user;
        } else {
            throw new RuntimeException("This user " + user.getId() + " already has a glossary, " + user.getGlossaries().getId());
        }
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
