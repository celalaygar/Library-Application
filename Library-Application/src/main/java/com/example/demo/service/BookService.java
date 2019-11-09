package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookOneDto;
import com.example.demo.dto.BookUpdateDto;
import com.example.demo.util.TPage;

import javassist.NotFoundException;

public interface BookService {
	public BookOneDto save(BookOneDto bookOneDto) throws Exception;
	public List<BookDto> getAll() throws NotFoundException;
	public TPage<BookDto> getAllPageable(Pageable pageable) throws NotFoundException ;
	public BookUpdateDto update(Long id, BookUpdateDto bookUpdateDto) throws NotFoundException;
	public BookOneDto getOne(Long id) throws NotFoundException ;
	public Boolean delete(Long id) throws NotFoundException;
	public List<BookDto> SearchBooksByName(String name) throws NotFoundException;
}
