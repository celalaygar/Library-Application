package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.example.demo.dto.StudenPatchtDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.util.TPage;

import javassist.NotFoundException;

public interface StudentService {
	public StudentDto save(@Valid StudentDto studentDto) throws Exception;
	public TPage<StudentDto> getAllPageable(Pageable pageable) throws NotFoundException;
	public List<StudentDto> getAll() throws NotFoundException;
	public StudentDto findById(Long id) throws NotFoundException;
	public StudentDto update(Long id, @Valid StudentDto studentDto) throws Exception;
	public StudentDto getBookForStudent(@Valid StudenPatchtDto studenPatchtDto) throws NotFoundException;
	public Boolean delete(Long id) throws NotFoundException;
	public StudentDto leaveBookForStudent(@Valid StudenPatchtDto studenPatchtDto) throws NotFoundException;
}
