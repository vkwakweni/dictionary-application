package com.mop.dictionaryApp.repository;

import com.mop.dictionaryApp.model.Glossary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlossaryRepository extends JpaRepository<Glossary, Integer> {
}
