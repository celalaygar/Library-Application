package com.example.demo.service.imp;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Author;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class AuthorServiceImp {
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final AuthorRepository authorRepository;
	
	
	
	public AuthorServiceImp(ModelMapper modelMapper,UserRepository userRepository,AuthorRepository authorRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository=userRepository;
		this.authorRepository=authorRepository;
	}
	
	public AuthorDto save(AuthorDto authorDto) {
		Author authorChecked=authorRepository.findByEmail(authorDto.getEmail());
		
		if(authorChecked !=null) {
			throw new IllegalArgumentException("User email already exist");
		}
		Author author=modelMapper.map(authorDto, Author.class); 
		authorRepository.save(author);
		authorDto.setId(author.getId());
		return authorDto;
	}
	
	public List<AuthorDto> getAll() throws NotFoundException {
		List<Author> authors=authorRepository.findAll();
		if(authors.size()<1) {
			throw new NotFoundException("Author don't already exist");
		}
		AuthorDto[] authorDto=modelMapper.map(authors, AuthorDto[].class);
		return Arrays.asList(authorDto);
	}
}
