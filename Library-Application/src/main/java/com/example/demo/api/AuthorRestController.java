package com.example.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorOneDto;
import com.example.demo.dto.AuthorUpdateDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookUpdateDto;
import com.example.demo.service.imp.AuthorServiceImp;
import com.example.demo.util.ApiPaths;
import com.example.demo.util.TPage;

import javassist.NotFoundException;

@RestController
@RequestMapping(ApiPaths.AuthorCtrl.CTRL)
@CrossOrigin
public class AuthorRestController {

	private final AuthorServiceImp authorServiceImp;

	public AuthorRestController(AuthorServiceImp authorServiceImp) {
		super();
		this.authorServiceImp = authorServiceImp;
	}

	// http://localhost:8182/api/author
	@GetMapping()
	public ResponseEntity<List<AuthorDto>> getAll() throws NotFoundException {
		List<AuthorDto> authorDtos = authorServiceImp.getAll();
		return ResponseEntity.ok(authorDtos);
	}

	// localhost:8182/api/author/pagination?page=1&size=3
	@GetMapping("/pagination")
	public ResponseEntity<TPage<AuthorDto>> getAllByPagination(Pageable pageable) throws NotFoundException {
		TPage<AuthorDto> data = authorServiceImp.getAllPageable(pageable);
		return ResponseEntity.ok(data);
	}

	// http://localhost:8182/api/author/find?name=name
	@GetMapping("/find")
	public ResponseEntity<List<AuthorDto>> findAllByName(@RequestParam String name) throws NotFoundException {
		List<AuthorDto> authorDtos = authorServiceImp.findAllByName(name);
		return ResponseEntity.ok(authorDtos);
	}

	// http://localhost:8182/api/author/2
	@GetMapping("/{id}")
	public ResponseEntity<AuthorOneDto> getOneAuthor(@PathVariable(name = "id", required = true) Long id)
			throws NotFoundException {
		return ResponseEntity.ok(authorServiceImp.getOne(id));
	}

	@PostMapping()
	public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
		return ResponseEntity.ok(authorServiceImp.save(authorDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AuthorUpdateDto> updateAuthor(@PathVariable(name = "id", required = true) Long id,
			@Valid @RequestBody AuthorUpdateDto authorUpdateDto) throws NotFoundException {
		return ResponseEntity.ok(authorServiceImp.update(id, authorUpdateDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBook(@PathVariable(name = "id", required = true) Long id)
			throws NotFoundException {
		return ResponseEntity.ok(authorServiceImp.delete(id));
	}
}
