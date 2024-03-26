package com.example.demo.users.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.demo.books.model.BookEntity;
import com.example.demo.core.model.BaseEntity;

public class UserEntity extends BaseEntity {
    private String login;
    private String mail;
    private String password;
    private final List<BookEntity> favoritebooks = new ArrayList<>();

    public UserEntity() {
        super();
    }

    public UserEntity(Long id, String login, String mail, String password, List<BookEntity> favoritebooks) {
        super(id);
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.favoritebooks.addAll(favoritebooks);
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
        this.favoritebooks.clear();
        this.favoritebooks.addAll(favoritebooks);
    }

    public List<BookEntity> addFavoritebook(BookEntity favoritebook) {
        favoritebooks.add(favoritebook);
        return favoritebooks;
    }

    public List<BookEntity> deleteFavoritebook(BookEntity favoritebook) {
        favoritebooks.remove(favoritebook);
        return favoritebooks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, mail, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final UserEntity other = (UserEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getLogin(), login)
                && Objects.equals(other.getMail(), mail)
                && Objects.equals(other.getPassword(), password)
                && Objects.equals(other.getFavoritebooks(), favoritebooks);
    }

}
