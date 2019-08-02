package com.example.demo.api;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorUpdateDto;
import com.example.demo.dto.BookUpdateDto;
import com.example.demo.dto.StudenPatchtDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.BookStatus;
import com.example.demo.model.City;
import com.example.demo.service.imp.AuthorServiceImp;
import com.example.demo.service.imp.StudentServiceImp;
import com.example.demo.util.ApiPaths;
import com.example.demo.util.TPage;

import javassist.NotFoundException;

@RestController
@RequestMapping(ApiPaths.StudentCtrl.CTRL)
@CrossOrigin
public class StudentRestController {

	private final StudentServiceImp studentServiceImp;

	public StudentRestController(StudentServiceImp studentServiceImp) {
		super();
		this.studentServiceImp = studentServiceImp;
	}

	@GetMapping()
	public ResponseEntity<List<StudentDto>> getAll() throws NotFoundException {
		List<StudentDto> customerDtos = studentServiceImp.getAll();
		return ResponseEntity.ok(customerDtos);
	}

	// localhost:8182/api/student/pagination?page=1&size=3
	@GetMapping("/pagination")
	public ResponseEntity<TPage<StudentDto>> getAllByPagination(Pageable pageable) throws NotFoundException {
		TPage<StudentDto> data = studentServiceImp.getAllPageable(pageable);
		return ResponseEntity.ok(data);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> selectStudent(@PathVariable(name = "id", required = true) Long id)
			throws NotFoundException {
		return ResponseEntity.ok(studentServiceImp.findById(id));
	}

	@PostMapping()
	public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) throws Exception {
		return ResponseEntity.ok(studentServiceImp.save(studentDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable(name = "id", required = true) Long id,
			@Valid @RequestBody StudentDto studentDto) throws Exception {
		return ResponseEntity.ok(studentServiceImp.update(id, studentDto));
	}

	@PatchMapping("/get-book")
	public ResponseEntity<StudentDto> getBookForStudent(@Valid @RequestBody StudenPatchtDto studenPatchtDto)
			throws NotFoundException {
		// @Valid @RequestBody Long bookId ) throws NotFoundException {
		return ResponseEntity.ok(studentServiceImp.getBookForStudent(studenPatchtDto));
	}

	@PatchMapping("/leave-book")
	public ResponseEntity<StudentDto> leaveBookForStudent(@Valid @RequestBody StudenPatchtDto studenPatchtDto)
			throws NotFoundException {

		return ResponseEntity.ok(studentServiceImp.leaveBookForStudent(studenPatchtDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBook(@PathVariable(name = "id", required = true) Long id)
			throws NotFoundException {
		return ResponseEntity.ok(studentServiceImp.delete(id));
	}

	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllBookStatus() {
		return ResponseEntity.ok(Arrays.asList(City.values()));
	}
}
