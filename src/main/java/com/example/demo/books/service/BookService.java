package com.example.demo.books.service;

import com.example.demo.authors.service.AuthorService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.error.NotFoundException;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository repository;
    private final AuthorService authorService;

    public BookService(BookRepository repository, AuthorService authorService) {
        this.repository = repository;
        this.authorService = authorService;
    }

    @Transactional(readOnly = true)
    public List<BookEntity> getAll(Long authorId) {
        authorService.get(authorId);
        if (authorId >= 0L) {
            return repository.findByAuthorId(authorId);
        } else {
            return repository.findById(authorId);
            // TODO сделать возврат всех эелементов
        }
    }

    @Transactional(readOnly = true)
    public BookEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BookEntity.class, id));
    }

    @Transactional
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
