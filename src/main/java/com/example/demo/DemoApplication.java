package com.example.demo;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.books.service.BookService;

import com.example.demo.authors.service.AuthorService;
import com.example.demo.users.service.UserService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	private final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	private final AuthorService authorService;
	private final BookService bookService;
	private final UserService userService;

	public DemoApplication(AuthorService authorService, BookService bookService, UserService userService) {
		this.authorService = authorService;
		this.bookService = bookService;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0 && Objects.equals("--populate", args[0])) {
			// authorService.create(new AuthorEntity(1L, "Author 1"));
			// authorService.create(new AuthorEntity(2L, "Author 2"));

			// bookService.create(new BookEntity(null, new AuthorEntity(3L, "Author 3"),
			// "Book 1", "ann", "inf", 2));
			// bookService.create(new BookEntity(null, new AuthorEntity(4L, "Author 4"),
			// "Book 4", "ann", "inf", 44));

		}
	}
}
