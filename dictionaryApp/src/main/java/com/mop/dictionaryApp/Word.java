package com.mop.dictionaryApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "senses")
// @IdClass(WordId.class)
@NamedQueries({
    @NamedQuery(name = "Word.findAll", query = "SELECT distinct w FROM Word w"),
    @NamedQuery(name = "Word.findByWordId", query = "SELECT distinct w FROM Word w WHERE w.id = :wordid")
})
public class Word {

    @Id
    @Column(name = "wordid")
    private Integer wordid;
    
    @Column(name = "senseid")
    private Integer synsetid;

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

    public Word(Integer word, Integer definition) {
        this.wordid = word;
        this.synsetid = definition;
    }

    // public Word(Integer word) {
    //     this.wordid = word;
    //     this.synsetSet = new ArrayList<>();
    // }

    public Integer getWordId() {
        return this.wordid;
    }

    public void setWordId(Integer id) {
        this.wordid = id;
    }

    public Integer getSynsetId() {
        return this.synsetid;
    }

    public void setSynsetId(Integer definition) {
        this.synsetid = definition;
    }

    // public void addSynset(Integer id) {
    //     this.synsetSet.add(id);
    // }
   

    @Override
    public String toString() {
        return this.wordid + ": " + this.synsetid;
    }
}
