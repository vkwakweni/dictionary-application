package com.mop.dictionaryApp.repository;

import com.mop.dictionaryApp.model.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface DefinitionRepository extends JpaRepository<Definition, Integer> {

    // Custom query to fetch definitions with associated words
    @Query("SELECT d FROM Definition d") // LEFT JOIN FETCH d.words WHERE d.synsetid = :synsetid")
    Definition findDefinitionWithWordsById(Integer synsetid);

    // Fetch all definitions along with their associated words
    @Query("SELECT DISTINCT d FROM Definition d") //LEFT JOIN FETCH d.words")
    List<Definition> findAllDefinitionsWithWords();
}
