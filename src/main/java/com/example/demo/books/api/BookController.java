package com.example.demo.books.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.configuration.Constants;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.service.BookService;
import com.example.demo.authors.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/book")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, AuthorService authorService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    public BookDto toDto(BookEntity entity) {
        return modelMapper.map(entity, BookDto.class);
    }

    public BookEntity toEntity(BookDto dto) {
        final BookEntity entity = modelMapper.map(dto, BookEntity.class);
        entity.setAuthor(authorService.get(dto.getAuthorId()));
        return entity;
    }

    @GetMapping
    public List<BookDto> getAll(@RequestParam(name = "authorId", defaultValue = "0") Long authorId) {
        return bookService.getAll(authorId).stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public BookDto get(@PathVariable(name = "id") Long id) {
        return toDto(bookService.get(id));
    }

    @PostMapping
    public BookDto create(@RequestBody @Valid BookDto dto) {
        return toDto(bookService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable(name = "id") Long id, @RequestBody BookDto dto) {
        return toDto(bookService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public BookDto delete(@PathVariable(name = "id") Long id) {
        return toDto(bookService.delete(id));
    }
}
