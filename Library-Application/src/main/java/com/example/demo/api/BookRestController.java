package com.example.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.service.imp.AuthorServiceImp;
import com.example.demo.service.imp.BookServiceImp;
import com.example.demo.util.ApiPaths;

import javassist.NotFoundException;

@RestController
@RequestMapping(ApiPaths.BookCtrl.CTRL)
public class BookRestController {
	
	private final AuthorServiceImp authorServiceImp;
	private final BookServiceImp bookServiceImp;
	
	public BookRestController(AuthorServiceImp authorServiceImp,
								BookServiceImp bookServiceImp) {
		super();
		this.authorServiceImp = authorServiceImp;
		this.bookServiceImp = bookServiceImp;
	}
	
	
    @GetMapping()
    public ResponseEntity<List<BookDto>> getAll() throws NotFoundException {
        List<BookDto> bookDtos = bookServiceImp.getAll();
        return ResponseEntity.ok(bookDtos);
    }
    

	@PostMapping()
	public ResponseEntity<BookDto> createProject(@Valid @RequestBody BookDto bookDto){
		return ResponseEntity.ok(bookServiceImp.save(bookDto));
	}
}
