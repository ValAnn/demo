package com.example.demo.users.api;

import java.util.List;

import com.example.demo.books.model.BookEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String mail;
    @NotBlank
    private String password;
    private List<BookEntity> favoritebooks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BookEntity> getFavoritebooks() {
        return favoritebooks;
    }

    public void setFavoritebooks(List<BookEntity> favoritebooks) {
        this.favoritebooks = favoritebooks;
    }
}
