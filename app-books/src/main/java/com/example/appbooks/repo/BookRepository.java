package com.example.appbooks.repo;

import com.example.appbooks.db.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookRepository implements IBookRepository{


    @PersistenceContext(unitName = "pu1")
    protected EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> myQuery=this.entityManager.createQuery("Select a from Book a", Book.class);
        return myQuery.getResultList();
    }

    @Override
    public Book findById(Integer id) {
        return this.entityManager.find(Book.class, id);
    }

    @Override
    public void create(Book book) {
        this.entityManager.persist(book);
    }

    @Override
    public Book update(Book book) {
        return this.entityManager.merge(book);
    }

    @Override
    public void deleteById(Integer id) {
        this.entityManager.remove(this.findById(id));
    }

}
