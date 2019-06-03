package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByName(String name);

}
