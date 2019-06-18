package com.example.demo.dto;

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
public class BookDtoForOneAuthor {
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String barcode;
	private String content;
	@NotNull
	private String publisher;
	
	private BookStatus bookStatus;
	@NotNull
	private Long authorId;
	private Long custoomerId;
}
