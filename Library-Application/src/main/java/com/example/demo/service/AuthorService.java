package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorOneDto;
import com.example.demo.dto.AuthorUpdateDto;
import com.example.demo.util.TPage;

import javassist.NotFoundException;

public interface AuthorService {
	public AuthorDto save(AuthorDto authorDto);
	public List<AuthorDto> getAll() throws NotFoundException;
	public TPage<AuthorDto> getAllPageable(Pageable pageable) throws NotFoundException;
	public List<AuthorDto> findAllByName(String name) throws NotFoundException ;
	public AuthorUpdateDto update(Long id, @Valid AuthorUpdateDto authorUpdateDto) throws NotFoundException;
	public AuthorOneDto getOne(Long id) throws NotFoundException;
	public Boolean delete(Long id) throws NotFoundException ;
}
