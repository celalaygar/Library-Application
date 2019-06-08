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
import com.example.demo.dto.CustomerDto;
import com.example.demo.service.imp.AuthorServiceImp;
import com.example.demo.service.imp.CustomerServiceImp;
import com.example.demo.util.ApiPaths;

import javassist.NotFoundException;

@RestController
@RequestMapping(ApiPaths.CustomerCtrl.CTRL)
public class CustomerRestController {
	
	private final CustomerServiceImp customerServiceImp;
	
	public CustomerRestController(CustomerServiceImp customerServiceImp) {
		super();
		this.customerServiceImp = customerServiceImp;
	}
	
	
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAll() throws NotFoundException {
        List<CustomerDto> customerDtos = customerServiceImp.getAll();
        return ResponseEntity.ok(customerDtos);
    }
    

	@PostMapping()
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto){
		return ResponseEntity.ok(customerServiceImp.save(customerDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name="id",required=true) 
														Long id,@Valid @RequestBody CustomerDto customerDto){
		return ResponseEntity.ok(customerServiceImp.update(id, customerDto));
	}
}
