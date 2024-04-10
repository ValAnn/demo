package com.example.demo.core.api;

import java.util.ArrayList;
import java.util.List;

public class PageDto<D> {
    private List<D> books = new ArrayList<>();
    private int booksCount;
    private int currentPage;
    private int currentSize;
    private int totalPages;
    private long totalBooks;
    private boolean isFirst;
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public List<D> getBooks() {
        return books;
    }

    public void setBooks(List<D> books) {
        this.books = books;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(int booksCount) {
        this.booksCount = booksCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(long totalBooks) {
        this.totalBooks = totalBooks;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
