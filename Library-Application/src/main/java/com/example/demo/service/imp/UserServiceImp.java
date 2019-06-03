package com.example.demo.service.imp;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserServiceImp {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	
	
	
	public UserServiceImp(ModelMapper modelMapper,UserRepository userRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository=userRepository;
	}
	
	public boolean save(UserDto userdto) {
		User userChecked=userRepository.findByEmail(userdto.getEmail());
		
		if(userChecked !=null) {
			throw new IllegalArgumentException("User email already exist");
		}
		User user=modelMapper.map(userdto, User.class);
		userRepository.save(user);
		return true;
	}
	
	public List<UserDto> getAll() throws NotFoundException {
		List<User> users=userRepository.findAll();
		if(users.size()<1) {
			throw new NotFoundException("Project don't already exist");
		}
		UserDto[] userdto=modelMapper.map(users, UserDto[].class);
		return Arrays.asList(userdto);
	}
}
