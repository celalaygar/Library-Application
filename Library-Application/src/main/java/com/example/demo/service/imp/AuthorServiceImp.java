package com.example.demo.service.imp;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorOneDto;
import com.example.demo.dto.AuthorUpdateDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookUpdateDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.TPage;

import javassist.NotFoundException;

@Service
public class AuthorServiceImp {
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final AuthorRepository authorRepository;
	
	
	public AuthorServiceImp(ModelMapper modelMapper,UserRepository userRepository,AuthorRepository authorRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository=userRepository;
		this.authorRepository=authorRepository;
	}
	
	public AuthorDto save(AuthorDto authorDto) {
		Author authorChecked=authorRepository.findByEmail(authorDto.getEmail());
		
		if(authorChecked !=null) {
			throw new IllegalArgumentException("User email already exist");
		}
		Author author=modelMapper.map(authorDto, Author.class); 
		authorRepository.save(author);
		authorDto.setId(author.getId());
		return authorDto;
	}
	
	public List<AuthorDto> getAll() throws NotFoundException {
		List<Author> authors=authorRepository.findAll();
		if(authors.size()<1) {
			throw new NotFoundException("Author don't already exist");
		}
		AuthorDto[] authorDto=modelMapper.map(authors, AuthorDto[].class);
		return Arrays.asList(authorDto);
	}
	public TPage<AuthorDto> getAllPageable(Pageable pageable) {
		Page<Author> page=authorRepository.findAll(PageRequest.of(pageable.getPageNumber(), 
				  pageable.getPageSize(), 
				  Sort.by(Sort.Direction.ASC, "id")));
		TPage<AuthorDto> tPage=new TPage<AuthorDto>();
		AuthorDto[] authorDtos=modelMapper.map(page.getContent(), AuthorDto[].class);
		Arrays.asList(authorDtos).forEach(author->{
			author.getBooks().forEach(aa->{ aa.setAuthor(null); });
		});
		tPage.setStat(page, Arrays.asList(authorDtos));
		return tPage;
	}
	public AuthorUpdateDto update(Long id, @Valid AuthorUpdateDto authorUpdateDto) throws NotFoundException {
		try {
			Author author=authorRepository.getOne(id);
	        author=modelMapper.map(authorUpdateDto, Author.class);
	        author.setId(id);
	        authorRepository.save(author);
	        authorUpdateDto.setId(author.getId());
	        return authorUpdateDto;
		} catch (Exception e) {
			throw new NotFoundException("User email dosen't exist");
		}
	}

	public AuthorOneDto getOne(Long id) {
		Author author=authorRepository.getOne(id);
		if(author == null) {
			throw new IllegalArgumentException("User email dosen't exist");
		}

		AuthorOneDto authorOneDto=modelMapper.map(author, AuthorOneDto.class);
		authorOneDto.setId(id);
		authorOneDto.getBooks().forEach(data->{ data.setAuthorId(id); });
        return authorOneDto;
	}

	public Boolean delete(Long id) throws NotFoundException {
		try {
			Author author=authorRepository.getOne(id);
			authorRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new NotFoundException("User email dosen't exist");
		}
	}


}
