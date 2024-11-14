package com.mop.dictionaryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mop.dictionaryApp.model.Word;


@Repository
public interface WordRepository extends JpaRepository<Word, Integer>{

    
}
