package com.mop.dictionaryApp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer>{
    
}
