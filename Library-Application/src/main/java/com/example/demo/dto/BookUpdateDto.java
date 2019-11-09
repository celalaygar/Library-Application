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

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class BookUpdateDto {

	private Long id;
	@NotNull
	private String name;

	private String content;

	@NotNull
	private Long authorId;

	@NotNull
	private String barcode;

	@NotNull
	private BookStatus bookStatus;

	private AuthorDtoForOneEntity author;

	@NotNull
	private String publisher;
}
