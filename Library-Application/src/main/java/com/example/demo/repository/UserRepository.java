package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByEmail(String email);

	@Query("select u from User u where u.username like %:username%")
	List<User> getByUsername(@Param("username") String username);

	User findByUsername(String username);

}
