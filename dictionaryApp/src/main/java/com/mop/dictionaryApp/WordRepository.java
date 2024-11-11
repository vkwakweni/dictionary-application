package com.mop.dictionaryApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordRepository extends JpaRepository<Word, Integer>{

}
