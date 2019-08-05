package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.demo.model.BookStatus;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class BookDtoForOneEntity {

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String barcode;

	private String content;

	@NotNull
	private String publisher;

	private BookStatus bookStatus;

	private StudentDtoForOneEntity student;
	
	@NotNull
	private Long authorId;

	private Long studentId;
}
