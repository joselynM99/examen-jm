package com.example.appbooks.repo;

import com.example.appbooks.db.Book;

import java.util.List;

public interface IBookRepository {
    List<Book> findAll();
    Book findById(Integer id);
    void create(Book book);
    Book update(Book book);
    void deleteById(Integer id);
}
