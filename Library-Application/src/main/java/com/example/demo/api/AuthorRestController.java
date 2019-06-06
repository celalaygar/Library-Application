package com.example.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorUpdateDto;
import com.example.demo.dto.BookUpdateDto;
import com.example.demo.service.imp.AuthorServiceImp;
import com.example.demo.util.ApiPaths;

import javassist.NotFoundException;

@RestController
@RequestMapping(ApiPaths.AuthorCtrl.CTRL)
public class AuthorRestController {
	
	private final AuthorServiceImp authorServiceImp;
	
	public AuthorRestController(AuthorServiceImp authorServiceImp) {
		super();
		this.authorServiceImp = authorServiceImp;
	}
	
	
    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAll() throws NotFoundException {
        List<AuthorDto> authorDtos = authorServiceImp.getAll();
        return ResponseEntity.ok(authorDtos);
    }
    

	@PostMapping()
	public ResponseEntity<AuthorDto> createProject(@Valid @RequestBody AuthorDto authorDto){
		return ResponseEntity.ok(authorServiceImp.save(authorDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AuthorUpdateDto> updateissueDto(@PathVariable(name="id",required=true) 
														Long id,@Valid @RequestBody AuthorUpdateDto authorUpdateDto){
		return ResponseEntity.ok(authorServiceImp.update(id, authorUpdateDto));
	}
}
