package com.example.demo.service.imp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorUpdateDto;
import com.example.demo.dto.BookUpdateDto;
import com.example.demo.dto.StudenPatchtDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class StudentServiceImp {
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final AuthorRepository authorRepository;
	private final StudentRepository studentRepository;
	private final BookRepository bookRepository;
	
	
	
	public StudentServiceImp(ModelMapper modelMapper,
			UserRepository userRepository,
			AuthorRepository authorRepository,
			StudentRepository studentRepository,
			BookRepository bookRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository=userRepository;
		this.authorRepository=authorRepository;
		this.studentRepository=studentRepository;
		this.bookRepository=bookRepository;
	}
	
	public StudentDto save(StudentDto studentDto) {
		Student customerChecked=studentRepository.findByEmail(studentDto.getEmail());
		
		if(customerChecked !=null) {
			throw new IllegalArgumentException("Student email already exist");
		}
		Student student=modelMapper.map(studentDto, Student.class); 
		studentRepository.save(student);
		studentDto.setId(student.getId());
		return studentDto;
	}
	
	public List<StudentDto> getAll() throws NotFoundException {
		List<Student> students=studentRepository.findAll();
		if(students.size()<1) {
			throw new NotFoundException("Customer don't already exist");
		}
		StudentDto[] studentDtos=modelMapper.map(students, StudentDto[].class);
		return Arrays.asList(studentDtos);
	}

	public StudentDto update(Long id, @Valid StudentDto studentDto) throws NotFoundException {
		Student student=studentRepository.getOne(id);
		if(student == null) {
			throw new IllegalArgumentException("Student dosen't exist");
		}

		student=modelMapper.map(studentDto, Student.class);
		student.setId(id);
		studentRepository.save(student);
		studentDto.setId(student.getId());
        return studentDto;
	}

	public StudentDto insertBookForStudent(Long id, @Valid StudenPatchtDto studenPatchtDto) throws NotFoundException {
		Student student=studentRepository.getOne(id);
		if(student == null) {
			throw new IllegalArgumentException("Student dosen't exist");
		}
		Optional<Book> bookChecked=bookRepository.findById(studenPatchtDto.getBookId());
		if(!bookChecked.isPresent()) {
			throw new NotFoundException("Book dosen't exist");
		}
		bookChecked.get().setStudent(student);
		bookRepository.save(bookChecked.get());
		student=studentRepository.getOne(id);
		return modelMapper.map(student, StudentDto.class);
	}
	
	
}
