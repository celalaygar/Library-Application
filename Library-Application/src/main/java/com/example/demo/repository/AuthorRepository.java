package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByEmail(String email);

}
