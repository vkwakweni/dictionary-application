package com.mop.dictionaryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mop.dictionaryApp.model.Definition;

@Repository
public interface DefinitionRepository extends JpaRepository<Definition, Integer>{
    
}
