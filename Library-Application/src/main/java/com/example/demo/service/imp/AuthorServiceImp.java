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
import com.example.demo.service.AuthorService;
import com.example.demo.util.TPage;

import javassist.NotFoundException;

@Service
public class AuthorServiceImp implements AuthorService{
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final AuthorRepository authorRepository;

	public AuthorServiceImp(ModelMapper modelMapper, UserRepository userRepository, AuthorRepository authorRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.authorRepository = authorRepository;
	}

	public AuthorDto save(AuthorDto authorDto) {
		Author authorChecked = authorRepository.findByEmail(authorDto.getEmail());
		if (authorChecked != null) {
			throw new IllegalArgumentException("User email already exist");
		}
		Author author = modelMapper.map(authorDto, Author.class);
		authorRepository.save(author);
		authorDto.setId(author.getId());
		return authorDto;
	}

	public List<AuthorDto> getAll() throws NotFoundException {
		List<Author> authors = authorRepository.findAll();
		if (authors.size() < 1) {
			throw new NotFoundException("Author don't already exist");
		}
		AuthorDto[] authorDtos = modelMapper.map(authors, AuthorDto[].class);

		return Arrays.asList(authorDtos);
	}

	public TPage<AuthorDto> getAllPageable(Pageable pageable) throws NotFoundException {
		try {
			Page<Author> page = authorRepository.findAll(PageRequest.of(pageable.getPageNumber(),
					pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id")));
			// Page<Author> page=authorRepository.findAll(pageable);
			TPage<AuthorDto> tPage = new TPage<AuthorDto>();
			AuthorDto[] authorDtos = modelMapper.map(page.getContent(), AuthorDto[].class);

			tPage.setStat(page, Arrays.asList(authorDtos));
			return tPage;
		} catch (Exception e) {
			throw new NotFoundException("User email dosen't exist : " + e);
		}
	}

	public List<AuthorDto> findAllByName(String name) throws NotFoundException {
		List<Author> authors = authorRepository.findByNameOrSurname(name, name);
		if (authors.size() < 1) {
			throw new NotFoundException("Author don't already exist");
		}
		AuthorDto[] authorDtos = modelMapper.map(authors, AuthorDto[].class);

		return Arrays.asList(authorDtos);
	}

	public AuthorUpdateDto update(Long id, @Valid AuthorUpdateDto authorUpdateDto) throws NotFoundException {
		Optional<Author> authorOpt = authorRepository.findById(id);
		if (!authorOpt.isPresent()) {
			throw new NotFoundException("User dosen't exist : " + id);
		}
		Author author = modelMapper.map(authorUpdateDto, Author.class);
		author.setId(id);
		authorRepository.save(author);
		authorUpdateDto.setId(author.getId());
		return authorUpdateDto;

	}

	public AuthorOneDto getOne(Long id) throws NotFoundException {

		Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent()) {
			throw new NotFoundException("User dosen't exist : " + id);
		}

		AuthorOneDto authorOneDto = modelMapper.map(author.get(), AuthorOneDto.class);
		authorOneDto.setId(id);
		authorOneDto.getBooks().forEach(data -> {
			data.setAuthorId(id);
		});
		return authorOneDto;

	}

	public Boolean delete(Long id) throws NotFoundException {

		Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent()) {
			throw new NotFoundException("User dosen't exist : " + id);
		}
		authorRepository.deleteById(id);
		return true;

	}

}
