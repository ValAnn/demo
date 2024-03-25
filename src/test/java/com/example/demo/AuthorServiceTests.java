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
import com.example.demo.authors.service.*;;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AuthorServiceTests {
    @Autowired
    private AuthorService authorService;

    @Test
    void getTest() {
        Assertions.assertThrows(NotFoundException.class, () -> authorService.get(0L));
    }

    @Test
    @Order(1)
    void createTest() {
        authorService.create(new AuthorEntity(null, "Альбус Персиваль Вульфрик Брайан Дамблдор"));
        authorService.create(new AuthorEntity(null, "Джон Рональд Руэл Толкин"));
        final AuthorEntity last = authorService
                .create(new AuthorEntity(null, "Арагорн, сын Араторна, потомок Исильдура"));
        Assertions.assertEquals(3, authorService.getAll().size());
        Assertions.assertEquals(last, authorService.get(3L));
    }

    @Test
    @Order(2)
    void updateTest() {
        final String test = "TEST";
        final AuthorEntity entity = authorService.get(3L);
        final String oldName = entity.getName();
        final AuthorEntity newEntity = authorService.update(3L, new AuthorEntity(1L, test));
        Assertions.assertEquals(3, authorService.getAll().size());
        Assertions.assertEquals(newEntity, authorService.get(3L));
        Assertions.assertEquals(test, newEntity.getName());
        Assertions.assertNotEquals(oldName, newEntity.getName());
    }

    @Test
    @Order(3)
    void deleteTest() {
        authorService.delete(3L);
        Assertions.assertEquals(2, authorService.getAll().size());
        final AuthorEntity last = authorService.get(2L);
        Assertions.assertEquals(2L, last.getId());

        final AuthorEntity newEntity = authorService
                .create(new AuthorEntity(null, "Арагорн, сын Араторна, потомок Исльдура"));
        Assertions.assertEquals(3, authorService.getAll().size());
        Assertions.assertEquals(4L, newEntity.getId());
    }
}
