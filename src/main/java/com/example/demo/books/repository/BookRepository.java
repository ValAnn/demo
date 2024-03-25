package com.example.demo.books.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.core.repository.MapRepository;
import com.example.demo.books.model.BookEntity;

@Repository
public class BookRepository extends MapRepository<BookEntity> {
}
