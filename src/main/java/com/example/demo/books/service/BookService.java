package com.example.demo.books.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.core.error.NotFoundException;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookEntity> getAll(Long authorId) {
        if (Objects.equals(authorId, 0L)) {
            return repository.getAll();
        }
        return repository.getAll().stream()
                .filter(book -> book.getAuthor().getId().equals(authorId))
                .toList();
    }

    public BookEntity get(Long id) {
        return Optional.ofNullable(repository.get(id))
                .orElseThrow(() -> new NotFoundException(id));
    }

    public BookEntity create(BookEntity entity) {
        return repository.create(entity);
    }

    public BookEntity update(Long id, BookEntity entity) {
        final BookEntity existsEntity = get(id);
        existsEntity.setAuthor(entity.getAuthor());
        existsEntity.setName(entity.getName());
        existsEntity.setAnnotation(entity.getAnnotation());
        existsEntity.setInfo(entity.getInfo());
        existsEntity.setNumber(entity.getNumber());
        return repository.update(existsEntity);
    }

    public BookEntity delete(Long id) {
        final BookEntity existsEntity = get(id);
        return repository.delete(existsEntity);
    }
}
