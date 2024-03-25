package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.authors.model.AuthorEntity;
import com.example.demo.books.api.BookController;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.repository.BookRepository;
import com.example.demo.books.service.BookService;
import com.example.demo.core.error.NotFoundException;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTests {
	@Autowired
	private UserService userService;
	private BookController bookController;
	private BookService bookService = new BookService(new BookRepository());

	@Test
	void getTest() {
		Assertions.assertThrows(NotFoundException.class, () -> userService.get(0L));
	}

	@Test
	@Order(1)
	void createTest() {

		userService.create(new UserEntity(null, "ValAnn", "ann@mail.ru", "123", new ArrayList<>()));
		final UserEntity last = userService
				.create(new UserEntity(null, "qwerty1", "qwerty1@mail.ru", "12345678", new ArrayList<>()));
		Assertions.assertEquals(2, userService.getAll().size());
		Assertions.assertEquals(last, userService.get(2L));
	}

	@Test
	@Order(2)
	void updateTest() {
		userService.addfavoritebook(1L, 1L);

		final String test = "TEST";
		final UserEntity entity = userService.get(2L);
		final String oldLogin = entity.getLogin();
		final UserEntity newEntity = userService.update(2L,
				new UserEntity(1L, test, entity.getMail(), entity.getPassword(), new ArrayList<>()));
		Assertions.assertEquals(2, userService.getAll().size());
		Assertions.assertEquals(newEntity, userService.get(2L));
		Assertions.assertEquals(test, newEntity.getLogin());
		Assertions.assertNotEquals(oldLogin, newEntity.getLogin());
	}

	@Test
	@Order(3)
	void addfavoritebooksTest() {

		userService.create(new UserEntity(null, "ValAnn", "ann@mail.ru", "123", new ArrayList()));

		userService.addfavoritebook(1L, 4L);
		userService.addfavoritebook(1L, 3L);
		userService.addfavoritebook(1L, 1L);
		Assertions.assertEquals(4, userService.getfavoritebooks(1L).size());

		userService.deletefavoritebook(1L, 1L);
		Assertions.assertEquals(3, userService.getfavoritebooks(1L).size());
	}

	@Test
	@Order(4)
	void deleteTest() {
		Assertions.assertEquals(3, userService.getAll().size());
		userService.delete(2L);
		Assertions.assertEquals(2, userService.getAll().size());
		final UserEntity last = userService.get(1L);
		Assertions.assertEquals(1L, last.getId());

		Assertions.assertEquals(2, userService.getAll().size());
	}
}
