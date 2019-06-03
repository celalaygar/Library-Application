package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="name",length=300)
	private String name;
	@Column(name="content",length=300)
	private String content;
	@Column(name="barcode",length=300)
	private String barcode;
	@Column(name="publisher",length=300)
	private String publisher;
    @JoinColumn(name = "author_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Author author;

}
