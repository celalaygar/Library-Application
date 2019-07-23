package com.example.demo.dto;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistirationRequest {

	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String email;
}
