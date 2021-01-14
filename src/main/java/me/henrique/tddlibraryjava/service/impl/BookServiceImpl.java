package me.henrique.tddlibraryjava.service.impl;

import me.henrique.tddlibraryjava.model.entity.Book;
import me.henrique.tddlibraryjava.model.repository.BookRepository;
import me.henrique.tddlibraryjava.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
