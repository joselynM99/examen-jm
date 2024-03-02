package com.example.appbooks.rest;

import com.example.appbooks.client.AuthorRestClient;
import com.example.appbooks.db.Book;
import com.example.appbooks.dto.BookDto;
import com.example.appbooks.repo.IBookRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

public class BookRest {
    @Inject
    IBookRepository repo;

    @Inject
    @RestClient
    AuthorRestClient authorClient;

    @GET
    public List<BookDto> findAll() {
        return repo.findAll().stream()
                .map(book->{
                    var author = authorClient.findById(book.getAuthorId());

                    var dto = BookDto.from(book);

                    String aname = String.format("%s %s",
                            author.getLastName(), author.getFirstName());

                    dto.setAuthorName( aname );

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        var book = repo.findById(id);

        if(book==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book).build();
    }

    @POST
    public Response create(Book obj) {
        obj.setId(null);

        repo.create(obj);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")

    public Response update(@PathParam("id")Integer id, Book obj) {

        Book b = repo.findById(id);

        b.setIsbn(obj.getIsbn());
        b.setTitle(obj.getTitle());
        b.setPrice(obj.getPrice());
        b.setAuthorId(obj.getAuthorId());

        return Response.ok()
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        repo.deleteById(id);

        return Response.ok()
                .build();
    }

}
