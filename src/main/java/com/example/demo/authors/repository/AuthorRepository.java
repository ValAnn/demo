package com.example.demo.authors.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.core.repository.MapRepository;
import com.example.demo.authors.model.AuthorEntity;

@Repository
public class AuthorRepository extends MapRepository<AuthorEntity> {
}
