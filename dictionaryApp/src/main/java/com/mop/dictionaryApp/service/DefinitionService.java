package com.mop.dictionaryApp.service;

import java.util.List;

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

    public List<Definition> getAllDefinitions() {
        return definitionRepository.findAll();
    }

}
