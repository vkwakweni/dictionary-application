
package com.groupproject.model;

import java.util.ArrayList;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "senses")
@SecondaryTable(name = "synsets", pkJoinColumns = @PrimaryKeyJoinColumn(name="synsetid"))
@SecondaryTable(name = "words", pkJoinColumns = @PrimaryKeyJoinColumn(name="wordid"))
@NamedQueries({ // what type of queries would go here?
    
})
public class Word implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "wordid")
    private Integer wordID;
    /*
     * we don't have definitinos in the same table as words right now, so, the possible
     * solutions are:
     * - https://docs.oracle.com/javaee/7/api/javax/persistence/SecondaryTable.html
     * - https://stackoverflow.com/questions/66982026/jpa-secondary-table-mapping-direction
     */
    @Column(name = "synsetid")
    private Integer[] definitionID;
    @Column(name = "lemma", table = "words")
    private String word;
    @Column(name = "lemma", table = "synsets")
    private ArrayList<String> definitions;
 
    // Constructors, Getters and Setters
    public Word() {}

    public Word(String word, ArrayList<String> definitions) {
        // if we make one table, we can maybe make the constructor with just ID
        this.word = word;
        this.definitions = definitions;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getDefinitions() {
        return definitions;
    }

    public void setDefinition(ArrayList<String> definitions) {
        this.definitions = definitions;
    }

}
