package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.demo.model.BookStatus;
import com.example.demo.model.Student;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class BookDto {

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String barcode;

	private String content;

	@NotNull
	private String publisher;

	private Long studentId;

	private BookStatus bookStatus;

	@NotNull
	private Long authorId;

	private AuthorDtoForOneEntity author;

	private StudentDtoForOneEntity student;

}
