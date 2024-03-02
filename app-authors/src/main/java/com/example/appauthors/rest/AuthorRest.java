package com.example.appauthors.rest;
import com.example.appauthors.db.Author;
import com.example.appauthors.repo.IAuthorRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AuthorRest {

    @Inject
    IAuthorRepository authorRepository;

    @GET
    public List<Author> findAll() {
        System.out.println("**************************");

        return authorRepository.findAll();
    }
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var book = authorRepository.findById(id);
        if (book==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book).build();
    }


    @POST
    public Response create(Author p) {
        authorRepository.create(p);

        return Response.status(Response.Status.CREATED.getStatusCode(), "author created").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Author authorObj) {
        Author author = authorRepository.findById(id);
        author.setFirstName(authorObj.getFirstName());
        author.setLastName(authorObj.getLastName());


        //rep.persistAndFlush(author);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        authorRepository.deleteById(id);

        return Response.ok( )
                .build();
    }
}
