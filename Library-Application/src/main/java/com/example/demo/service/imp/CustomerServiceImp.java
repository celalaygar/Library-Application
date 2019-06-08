package com.example.demo.service.imp;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorUpdateDto;
import com.example.demo.dto.BookUpdateDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Customer;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class CustomerServiceImp {
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final AuthorRepository authorRepository;
	private final CustomerRepository customerRepository;
	
	
	
	public CustomerServiceImp(ModelMapper modelMapper,
			UserRepository userRepository,
			AuthorRepository authorRepository,
			CustomerRepository customerRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository=userRepository;
		this.authorRepository=authorRepository;
		this.customerRepository=customerRepository;
	}
	
	public CustomerDto save(CustomerDto customerDto) {
		Customer customerChecked=customerRepository.findByEmail(customerDto.getEmail());
		
		if(customerChecked !=null) {
			throw new IllegalArgumentException("Customer email already exist");
		}
		Customer customer=modelMapper.map(customerDto, Customer.class); 
		customerRepository.save(modelMapper.map(customerDto, Customer.class));
		customerDto.setId(customer.getId());
		return customerDto;
	}
	
	public List<CustomerDto> getAll() throws NotFoundException {
		List<Customer> customers=customerRepository.findAll();
		if(customers.size()<1) {
			throw new NotFoundException("Customer don't already exist");
		}
		CustomerDto[] customerDtos=modelMapper.map(customers, CustomerDto[].class);
		return Arrays.asList(customerDtos);
	}

	public CustomerDto update(Long id, @Valid CustomerDto customerDto) {
		Customer customer=customerRepository.getOne(id);
		if(customer == null) {
			throw new IllegalArgumentException("Customer email dosen't exist");
		}

		customer=modelMapper.map(customerDto, Customer.class);
		customer.setId(id);
		customerRepository.save(customer);
		customerDto.setId(customer.getId());
        return customerDto;
	}
}
