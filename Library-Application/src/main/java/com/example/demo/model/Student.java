package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "tcNo", length = 11, unique = true)
	private String tcNo;

	@NotNull
	@Column(name = "fullname", length = 100, unique = true)
	private String fullname;

	@NotNull
	@Column(name = "email", length = 100, unique = true)
	private String email;

	@Column(name = "university", length = 3000)
	private String university;

	@Column(name = "department", length = 3000)
	private String department;

	@NotNull
	@Column(name = "phone", length = 100)
	private String phone;

	@Column(name = "address", length = 100)
	private String address;

	@Column(name = "city", length = 100)
	@Enumerated(EnumType.STRING)
	private City city;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Book> books;
}
