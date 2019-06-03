package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="author")
public class AuthorDto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name="name",length=100,unique=true)
	private String name;
	
	@NotNull
	@Column(name="surname",length=100)
	private String surname;
	
	@Column(name="about",length=300)
	private String about;
	
	@Column(name="email",length=100)
	private String email;
	
	@Column(name="phone",length=100)
	private String phone;


}
