package com.example.appauthors.repo;

import com.example.appauthors.db.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


import java.util.List;

@ApplicationScoped
@Transactional
public class AuthorRepository implements IAuthorRepository{
    @PersistenceContext(unitName = "pu1")
    protected EntityManager entityManager;

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> myQuery=this.entityManager.createQuery("Select a from Author a", Author.class);
        return myQuery.getResultList();
    }

    @Override
    public Author findById(Integer id) {
        return this.entityManager.find(Author.class, id);
    }

    @Override
    public void create(Author author) {
        this.entityManager.persist(author);
    }

    @Override
    public Author update(Author author) {
        return this.entityManager.merge(author);
    }

    @Override
    public void deleteById(Integer id) {
        this.entityManager.remove(this.findById(id));
    }
}

