package com.example.demo.books.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.core.repository.MapRepository;
import com.example.demo.books.model.BookEntity;
import com.example.demo.books.model.BookGrouped;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long>,
        PagingAndSortingRepository<BookEntity, Long> {

    Optional<BookEntity> findOneByAuthorIdAndId(long authorId, long id);

    List<BookEntity> findByAuthorId(long authorId);

    List<BookEntity> findById(long Id);

    Page<BookEntity> findByAuthorId(long authorId, Pageable pageable);

    // @Query("select "
    // + "t as type, "
    // + "coalesce(sum(o.price), 0) as totalPrice, "
    // + "coalesce(sum(o.count), 0) as totalCount "
    // + "from TypeEntity t left join OrderEntity o on o.type = t and o.user.id = ?1
    // "
    // + "group by t order by t.id")
    // List<BookGrouped> getOrdersTotalByType(long userId);
}