package com.example.demo.users.api;

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

import com.example.demo.books.model.BookEntity;
import com.example.demo.books.service.BookService;
import com.example.demo.core.configuration.Constants;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/user")
public class UserController {
    private final UserService userService;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper, BookService bookService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }

    private UserDto toDto(UserEntity entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    private UserEntity toEntity(UserDto dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable(name = "id") Long id) {
        return toDto(userService.get(id));
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return toDto(userService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable(name = "id") Long id, @RequestBody UserDto dto) {
        return toDto(userService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable(name = "id") Long id) {
        return toDto(userService.delete(id));
    }

    @PostMapping("/{id}/favoritebooks/{favoritebook_id}")
    public UserDto addfavoritebook(@PathVariable(name = "id") Long id, Long favoritebook_id) {
        return toDto(
                userService.addfavoritebook(id, bookService.get(favoritebook_id)));
    }

    @DeleteMapping("/{id}/favoritebooks/{favoritebook_id}")
    public UserDto deletefavoritebook(@PathVariable(name = "id") Long id, Long favoritebook_id) {
        return toDto(
                userService.deletefavoritebook(id, bookService.get(favoritebook_id)));
    }
}
