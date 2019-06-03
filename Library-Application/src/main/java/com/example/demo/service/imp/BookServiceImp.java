package com.example.demo.service.imp;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class BookServiceImp {
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	
	
	
	public BookServiceImp(	ModelMapper modelMapper,
							UserRepository userRepository,
							AuthorRepository authorRepository,
							BookRepository bookRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository=userRepository;
		this.authorRepository=authorRepository;
		this.bookRepository=bookRepository;
	}
	
	public boolean save(BookDto bookDto) {
		Book bookChecked=bookRepository.findByName(bookDto.getName());
		if(bookChecked !=null) {
			throw new IllegalArgumentException("User email already exist");
		}
		Book book=modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);
		return true;
	}
	
	public List<BookDto> getAll() throws NotFoundException {
		List<Book> books=bookRepository.findAll();
		if(books.size()<1) {
			throw new NotFoundException("Book don't already exist");
		}
		BookDto[] bookDtos=modelMapper.map(books, BookDto[].class);
		return Arrays.asList(bookDtos);
	}
}
