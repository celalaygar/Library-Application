package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.demo.model.City;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class StudentDtoForOneEntity {

	private Long id;

	private String tcNo;

	@NotNull
	private String fullname;

	private String university;

	private String department;

	@NotNull
	private String email;

	@NotNull
	private String phone;

	private String address;

	private City city;

}
