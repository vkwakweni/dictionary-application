package com.mop.dictionaryApp.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mop.dictionaryApp.model.Definition;
import com.mop.dictionaryApp.repository.DefinitionRepository;

@Transactional
@Service
public class DefinitionService {
    
    @Autowired
    private DefinitionRepository definitionRepository;

    @PersistenceContext
    private EntityManager em;

    public DefinitionService(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    // Fetch a single definition with its words
    @Transactional
    public Definition getDefinitionWithWordsById(Integer synsetid) {
        return definitionRepository.findDefinitionWithWordsById(synsetid);
    }
    

    public List<Definition> getAllDefinitions() {
        return definitionRepository.findAll();
    }

}
