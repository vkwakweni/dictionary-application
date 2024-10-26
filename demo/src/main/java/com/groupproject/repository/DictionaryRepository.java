package com.groupproject.repository;

import com.groupproject.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<Word, Integer> {

    
}
