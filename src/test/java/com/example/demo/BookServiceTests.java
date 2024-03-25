package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.core.error.NotFoundException;
import com.example.demo.authors.model.AuthorEntity;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.service.BookService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BookServiceTests {
	@Autowired
	private BookService bookService;

	@Test
	void getTest() {
		Assertions.assertThrows(NotFoundException.class, () -> bookService.get(0L));
	}

	@Test
	@Order(1)
	void createTest() {
		bookService.create(new BookEntity(null, new AuthorEntity(1L, "Author 1"), "Book 1", "ann", "in", 2));
		bookService.create(new BookEntity(null, new AuthorEntity(2L, "Author 2"), "Book 2", "ann", "in", 2));

		Assertions.assertEquals(1, bookService.getAll(1L).size());
	}

	@Test
	@Order(2)
	void updateTest() {
		final String test = "TEST";
		final BookEntity entity = bookService.get(2L);
		final String oldName = entity.getName();
		final BookEntity newEntity = bookService.update(1L, new BookEntity(null, entity.getAuthor(), test,
				entity.getAnnotation(), entity.getInfo(), entity.getNumber()));
		for (BookEntity book : bookService.getAll(null)) {
			System.out.println("ID: " + book.getId() + ", Name: " + book.getName() + " " + book.getAuthor());
			Assertions.assertEquals(0, bookService.getAll(1L).size());
			Assertions.assertEquals(test, newEntity.getName());
			Assertions.assertNotEquals(oldName, newEntity.getName());
		}
	}

	@Test
	@Order(3)
	void deleteTest() {
		bookService.delete(2L);
		Assertions.assertEquals(0, bookService.getAll(null).size());

		final BookEntity newEntity = bookService
				.create(new BookEntity(null, new AuthorEntity(4L, "author"), "Book 1", "ann", "in", 2));

		Assertions.assertEquals(0, bookService.getAll(null).size());
		Assertions.assertEquals(3L, newEntity.getId());
	}
}
