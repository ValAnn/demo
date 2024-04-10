package com.example.demo.core.api;

import java.util.function.Function;

import org.springframework.data.domain.Page;

public class PageDtoMapper {
    private PageDtoMapper() {
    }

    public static <D, E> PageDto<D> toDto(Page<E> page, Function<E, D> mapper) {
        final PageDto<D> dto = new PageDto<>();
        dto.setBooks(page.getContent().stream().map(mapper::apply).toList());
        dto.setBooksCount(page.getNumberOfElements());
        dto.setCurrentPage(page.getNumber());
        dto.setCurrentSize(page.getSize());
        dto.setTotalPages(page.getTotalPages());
        dto.setTotalBooks(page.getTotalElements());
        dto.setFirst(page.isFirst());
        dto.setLast(page.isLast());
        dto.setHasNext(page.hasNext());
        dto.setHasPrevious(page.hasPrevious());
        return dto;
    }
}
