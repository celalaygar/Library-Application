package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users" , indexes = {@Index(name = "idx_username", columnList = "uname")})
public class UserDto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="uname",length=100,unique=true)
	private String username;
	
	@Column(name="pwd",length=300)
	private String password;	
	
	@Column(name="surname",length=100)
	private String surname;
	
	@Column(name="email",length=100)
	private String email;



}
