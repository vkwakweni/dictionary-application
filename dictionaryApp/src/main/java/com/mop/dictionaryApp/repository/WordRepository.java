package com.mop.dictionaryApp.repository;

import com.mop.dictionaryApp.model.Word;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    @Query("SELECT w from Word w WHERE w.wordid = :wordid")
    Optional<Word> findWordWithDefinitionsById(Integer wordid);

    @Query("SELECT w from Word w")
    List<Word> findAllWordsWithDefinitions();

    @Query(value = "CALL searchWordsByPattern(:pattern)", nativeQuery = true)
    List<String> findWordsByPattern(@Param("pattern") String pattern);

    @Query("SELECT distinct w.wordid FROM Word w WHERE w.lemma = :lemma")
    Integer findWordByLemma(String lemma);

    @Query(value = "CALL GetDefinitionsByPOS(:lemma, :pos)", nativeQuery = true)
    List<String> getDefinitionsByPOS(@Param("lemma") String lemma, @Param(":pos") String pos);
}
