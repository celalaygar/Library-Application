package com.example.demo.service.imp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudenPatchtDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Book;
import com.example.demo.model.Student;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentService;
import com.example.demo.util.TPage;

import javassist.NotFoundException;

@Service
public class StudentServiceImp implements StudentService {
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final AuthorRepository authorRepository;
	private final StudentRepository studentRepository;
	private final BookRepository bookRepository;

	public StudentServiceImp(ModelMapper modelMapper, UserRepository userRepository, AuthorRepository authorRepository,
			StudentRepository studentRepository, BookRepository bookRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.authorRepository = authorRepository;
		this.studentRepository = studentRepository;
		this.bookRepository = bookRepository;
	}

	public StudentDto save(@Valid StudentDto studentDto) throws Exception {
		List<Student> list = studentRepository.findByEmail(studentDto.getEmail().trim());

		if(list.size()>0){
			throw new Exception("Student email already exist : " + studentDto.getEmail());
		}
		list = studentRepository.findByTcNo(studentDto.getTcNo().trim());
		if(list.size()>0){
			throw new Exception("Student Tc no already exist : " + studentDto.getTcNo());
		}
		Student student = modelMapper.map(studentDto, Student.class);
		studentRepository.save(student);
		studentDto.setId(student.getId());
		return studentDto;
	}

	public TPage<StudentDto> getAllPageable(Pageable pageable) throws NotFoundException {
		try {
			Page<Student> page = studentRepository.findAll(PageRequest.of(pageable.getPageNumber(),
					pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id")));
			// Page<Author> page=authorRepository.findAll(pageable);
			TPage<StudentDto> tPage = new TPage<StudentDto>();
			StudentDto[] studentDtos = modelMapper.map(page.getContent(), StudentDto[].class);

			tPage.setStat(page, Arrays.asList(studentDtos));
			return tPage;
		} catch (Exception e) {
			throw new NotFoundException("User email dosen't exist : " + e);
		}
	}

	public List<StudentDto> getAll() throws NotFoundException {
		List<Student> students = studentRepository.findAll();
		if (students.size() < 1) {
			throw new NotFoundException("Customer don't already exist");
		}
		StudentDto[] studentDtos = modelMapper.map(students, StudentDto[].class);
		return Arrays.asList(studentDtos);
	}

	public StudentDto findById(Long id) throws NotFoundException {
		Optional<Student> studentOpt = studentRepository.findById(id);
		if (!studentOpt.isPresent()) {
			throw new NotFoundException("Student dosen't exist");
		}
		return modelMapper.map(studentOpt.get(), StudentDto.class);
	}

	public StudentDto update(Long id, @Valid StudentDto studentDto) throws Exception {
		Optional<Student> studentOpt = studentRepository.findById(id);
		if (!studentOpt.isPresent()) {
			throw new NotFoundException("Student dosen't exist");
		}
		Student student = modelMapper.map(studentDto, Student.class);
		student.setId(id);
		studentRepository.save(student);
		studentDto.setId(student.getId());
		return studentDto;
	}

	public StudentDto getBookForStudent(@Valid StudenPatchtDto studenPatchtDto) throws NotFoundException {
		Optional<Student> studentOpt = studentRepository.findById(studenPatchtDto.getStudentId());
		if (!studentOpt.isPresent()) {
			throw new NotFoundException("Student dosen't exist");
		}
		Optional<Book> bookChecked = bookRepository.findById(studenPatchtDto.getBookId());
		if (!bookChecked.isPresent()) {
			throw new NotFoundException("Book dosen't exist");
		}
		bookChecked.get().setStudent(studentOpt.get());
		bookRepository.save(bookChecked.get());
		return modelMapper.map(studentRepository.getOne(studenPatchtDto.getStudentId()), StudentDto.class);
	}

	public Boolean delete(Long id) throws NotFoundException {
		Optional<Student> studentOpt = studentRepository.findById(id);
		if (!studentOpt.isPresent()) {
			throw new NotFoundException("Student dosen't exist");
		}
		if (studentOpt.get().getBooks().size() > 0) {
			studentOpt.get().getBooks().forEach(book -> {
				book.setStudent(null);
				bookRepository.save(book);
			});
		}
		studentOpt.get().setBooks(null);
		studentRepository.delete(studentOpt.get());
		return true;

	}

	public StudentDto leaveBookForStudent(@Valid StudenPatchtDto studenPatchtDto) throws NotFoundException {
		Optional<Student> studentOpt = studentRepository.findById(studenPatchtDto.getStudentId());
		if (!studentOpt.isPresent()) {
			throw new IllegalArgumentException("Student dosen't exist");
		}
		Optional<Book> bookChecked = bookRepository.findById(studenPatchtDto.getBookId());
		if (!bookChecked.isPresent()) {
			throw new NotFoundException("Book dosen't exist");
		}

		bookChecked.get().setStudent(null);
		bookRepository.save(bookChecked.get());

		studentOpt.get().getBooks().remove(bookChecked.get());
		studentRepository.save(studentOpt.get());

		Student student = studentRepository.getOne(studenPatchtDto.getStudentId());
		return modelMapper.map(student, StudentDto.class);
	}

}
