package com.mop.dictionaryApp.repository;

import com.mop.dictionaryApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
