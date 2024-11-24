package com.mop.dictionaryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.mop.dictionaryApp.service.DefinitionService;

@RestController
public class DefinitionController {

    @Autowired
    private DefinitionService definitionService;

    public DefinitionController(DefinitionService definitionService) {
        this.definitionService = definitionService;
    }
    
}
