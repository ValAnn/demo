package com.example.demo.authors.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.configuration.Constants;
import com.example.demo.authors.model.AuthorEntity;
import com.example.demo.authors.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/author")
public class AuthorController {
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    private AuthorDto toDto(AuthorEntity entity) {
        return modelMapper.map(entity, AuthorDto.class);
    }

    private AuthorEntity toEntity(AuthorDto dto) {
        return modelMapper.map(dto, AuthorEntity.class);
    }

    @GetMapping
    public List<AuthorDto> getAll() {
        return authorService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public AuthorDto get(@PathVariable(name = "id") Long id) {
        return toDto(authorService.get(id));
    }

    @PostMapping
    public AuthorDto create(@RequestBody @Valid AuthorDto dto) {
        return toDto(authorService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable(name = "id") Long id, @RequestBody AuthorDto dto) {
        return toDto(authorService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public AuthorDto delete(@PathVariable(name = "id") Long id) {
        return toDto(authorService.delete(id));
    }
}
