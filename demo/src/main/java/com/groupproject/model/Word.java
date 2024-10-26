
package com.groupproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

@Entity
@Table(name = "words")
@SecondaryTable(name = "synsets")
@NamedQueries({ // what type of queries would go here?
    
})
public class Word {
    @Column(name = "id")
    private Integer id;
    @Column(name = "lemma")
    private String word;
    /*
     * we don't have definitinos in the same table as words right now, so, the possible
     * solutions are:
     * - https://docs.oracle.com/javaee/7/api/javax/persistence/SecondaryTable.html
     * - https://stackoverflow.com/questions/66982026/jpa-secondary-table-mapping-direction
     */
    @Column(name = "definition", table = "synset")
    private String definition;

    // Constructors, Getters and Setters
    public Word() {}

    public Word(String word, String definition) {
        // if we make one table, we can maybe make the constructor with just ID
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

}
