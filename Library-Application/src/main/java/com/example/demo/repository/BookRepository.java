package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Author;
import com.example.demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByName(String name);

	@Query("select b from Book b where b.name like %:name%")
	List<Book> SearchBooksByName(String name);

}
