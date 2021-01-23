package me.henrique.tddlibraryjava.service;

import me.henrique.tddlibraryjava.model.entity.Book;

import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Optional<Book> getById(Long id);

    void delete(Book book);

    Book update(Book book);
}
