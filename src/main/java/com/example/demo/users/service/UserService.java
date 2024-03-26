package com.example.demo.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.books.model.BookEntity;
import com.example.demo.books.repository.BookRepository;
import com.example.demo.books.service.BookService;
import com.example.demo.core.error.NotFoundException;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;
    public BookService bookService = new BookService(new BookRepository());

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> getAll() {
        return repository.getAll();
    }

    public UserEntity get(Long id) {
        return Optional.ofNullable(repository.get(id))
                .orElseThrow(() -> new NotFoundException(id));
    }

    public List<BookEntity> getfavoritebooks(Long id) {
        return Optional.ofNullable(repository.get(id).getFavoritebooks())
                .orElseThrow(() -> new NotFoundException(id));
    }

    public UserEntity create(UserEntity entity) {
        return repository.create(entity);
    }

    public UserEntity update(Long id, UserEntity entity) {
        final UserEntity existsEntity = get(id);
        existsEntity.setLogin(entity.getLogin());
        existsEntity.setMail(entity.getMail());
        existsEntity.setPassword(entity.getPassword());
        return repository.update(existsEntity);
    }

    public UserEntity delete(Long id) {
        final UserEntity existsEntity = get(id);
        return repository.delete(existsEntity);
    }

    public UserEntity addfavoritebook(Long id, BookEntity entity) {
        final UserEntity existsEntity = get(id);
        existsEntity.addFavoritebook(entity);
        return repository.update(existsEntity);
    }

    public UserEntity deletefavoritebook(Long id, BookEntity favoritebook) {
        final UserEntity existsEntity = get(id);
        existsEntity.deleteFavoritebook(favoritebook);
        return repository.update(existsEntity);
    }

}
