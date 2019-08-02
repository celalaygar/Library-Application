package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;
import com.example.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByEmail(String email);
	List<Student> findByTcNo(String tcNo);

}
