package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "name", length = 300)
	private String name;

	@Column(name = "content", length = 3000)
	private String content;

	@Column(name = "barcode", length = 100, unique = true)
	private String barcode;

	@Column(name = "publisher", length = 300)
	private String publisher;

	@Column(name = "bookStatus", length = 50)
	@Enumerated(EnumType.STRING)
	private BookStatus bookStatus;

	@NotNull
	@JoinColumn(name = "author_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Author author;

	@JoinColumn(name = "student_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Student student;
}
