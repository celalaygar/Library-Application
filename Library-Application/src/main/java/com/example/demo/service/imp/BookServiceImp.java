package com.example.demo.service.imp;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorOneDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookOneDto;
import com.example.demo.dto.BookUpdateDto;
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
	
	public BookDto save(BookDto bookDto) {
		Book bookChecked=bookRepository.findByName(bookDto.getName());
		Author author=authorRepository.getOne(bookDto.getAuthorId());
		if(bookChecked !=null) {
			throw new IllegalArgumentException("book already exist");
		}
		if(author.getId() != bookDto.getAuthorId()) {
			throw new IllegalArgumentException("Author does not match");
		}
		Book book=modelMapper.map(bookDto, Book.class);
		book.setAuthor(author);
		bookRepository.save(book);
		bookDto.setId(book.getId());
		bookDto.setAuthor(modelMapper.map(author, AuthorDto.class));
		return bookDto;
	}
	
	public List<BookDto> getAll() throws NotFoundException {
		List<Book> books=bookRepository.findAll();
		if(books.size()<1) {
			throw new NotFoundException("Book don't already exist");
		}
		BookDto[] bookDtos=modelMapper.map(books, BookDto[].class);
		List<BookDto> listbookDtos=Arrays.asList(bookDtos);
		listbookDtos.forEach(data->data.setAuthorId(data.getAuthor().getId()));
		return listbookDtos;
	}
	
	
    @Transactional
    public BookUpdateDto update(Long id, BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.getOne(id);
        Author author =  authorRepository.getOne(bookUpdateDto.getAuthorId());
        
        book.setName(bookUpdateDto.getName());
        book.setAuthor(author);
        book.setBarcode(bookUpdateDto.getBarcode());
        book.setContent(bookUpdateDto.getContent());
        book.setPublisher(bookUpdateDto.getPublisher());

        bookRepository.save(book);
        bookUpdateDto=modelMapper.map(book, BookUpdateDto.class);
        bookUpdateDto.setAuthorId(author.getId());
        return bookUpdateDto;
    }
	public BookOneDto getOne(Long id) throws NotFoundException {
		try {
			Book book = bookRepository.getOne(id);
			BookOneDto bookOneDto=modelMapper.map(book, BookOneDto.class);
			bookOneDto.setId(id);
			bookOneDto.setAuthorId(bookOneDto.getAuthor().getId());
	        return bookOneDto;
		} catch (Exception e) {
			throw new NotFoundException("Book does not exist id : "+id);
		}
	}

	public Boolean delete(Long id) throws NotFoundException {
		try {
			Book book = bookRepository.getOne(id);
			bookRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new NotFoundException("Book does not exist id : "+id);
		}
	}
}
