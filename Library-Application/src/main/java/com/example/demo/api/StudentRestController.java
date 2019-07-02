package com.example.demo.api;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@PostMapping()
	public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
		return ResponseEntity.ok(studentServiceImp.save(studentDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable(name = "id", required = true) Long id,
			@Valid @RequestBody StudentDto studentDto) throws NotFoundException {
		return ResponseEntity.ok(studentServiceImp.update(id, studentDto));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<StudentDto> insertBookForStudent(@PathVariable(name = "id", required = true) Long id,
			@Valid @RequestBody StudenPatchtDto studenPatchtDto) throws NotFoundException {
		
		return ResponseEntity.ok(studentServiceImp.insertBookForStudent(id, studenPatchtDto));
	}
	
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllBookStatus() {
        return ResponseEntity.ok(Arrays.asList(City.values()));
    }
}
