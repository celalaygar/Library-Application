package com.example.demo.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AuthorDto {

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String surname;

	private String about;

	private String email;

	private String phone;

	private List<BookDtoForOneEntity> books;

}
