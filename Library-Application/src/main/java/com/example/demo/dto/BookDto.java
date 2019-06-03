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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="book")
public class BookDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Column(name="name",length=300,unique = true)
	private String name;
	
	@Column(name="content",length=300)
	private String content;
	
    @JoinColumn(name = "author_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private AuthorDto author;


}
