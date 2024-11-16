package com.mop.dictionaryApp.repository;

import com.mop.dictionaryApp.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    
    @Query("SELECT w FROM Word w LEFT JOIN FETCH w.definitions WHERE w.wordid = :wordid")
    Word findWordWithDefinitionsById(Integer wordid);

  
    @Query("SELECT DISTINCT w FROM Word w LEFT JOIN FETCH w.definitions")
    List<Word> findAllWordsWithDefinitions();
}
