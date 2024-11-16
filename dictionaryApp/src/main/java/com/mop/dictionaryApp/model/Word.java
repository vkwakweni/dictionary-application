package com.mop.dictionaryApp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "senses")
// @IdClass(WordId.class)
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
    private List<Definition> synsetid = new ArrayList<Definition>() ;

    // private List<Integer> createtSynsetset() {
    //     List<Integer> synsetSet = new ArrayList<>();
    //     String synsets = "SELECT synset_id FROM senses WHERE wordid = " + this.wordid.toString();
    //     try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordnet31", "root", "null"); 
    //         Statement statement = conn.prepareStatement(synsets);) {
    //             ResultSet resultSet = statement.executeQuery(synsets);
    //             while (resultSet.next()) {
    //                 synsetSet.add(resultSet.getInt(1));
    //             }
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //     }
    //     return synsetSet;
    //     }  

    public Word() {}

    // public Word(Integer word, Integer definition) {
    //     this.wordid = word;
    //     this.synsetid = definition;
    // }

    // public Word(Integer word) {
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
        return this.synsetid;
    }

    public List<Definition> setSynsets(List<Definition> synsets) {
        return this.synsetid = synsets;
    }
   
    @Override
    public String toString() {
        return this.wordid + ": " + this.synsetid;
    }
}
