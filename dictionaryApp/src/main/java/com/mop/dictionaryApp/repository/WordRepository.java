package com.mop.dictionaryApp.repository;

import com.mop.dictionaryApp.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    @Query("SELECT w from Word w WHERE w.wordid = :wordid")
    Optional<Word> findWordWithDefinitionsById(Integer wordid);

    @Query("SELECT w from Word w")
    List<Word> findAllWordsWithDefinitions();

}
