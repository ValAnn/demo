package com.example.demo.authors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.core.error.NotFoundException;
import com.example.demo.authors.model.AuthorEntity;
import com.example.demo.authors.repository.AuthorRepository;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<AuthorEntity> getAll() {
        return repository.getAll();
    }

    public AuthorEntity get(Long id) {
        return Optional.ofNullable(repository.get(id))
                .orElseThrow(() -> new NotFoundException(id));
    }

    public AuthorEntity create(AuthorEntity entity) {
        return repository.create(entity);
    }

    public AuthorEntity update(Long id, AuthorEntity entity) {
        final AuthorEntity existsEntity = get(id);
        existsEntity.setName(entity.getName());
        return repository.update(existsEntity);
    }

    public AuthorEntity delete(Long id) {
        final AuthorEntity existsEntity = get(id);
        return repository.delete(existsEntity);
    }
}
