package com.flipkart.handsonlearning.resources;

import com.flipkart.handsonlearning.core.Author;
import com.flipkart.handsonlearning.core.Book;
import com.flipkart.handsonlearning.db.AuthorDAO;
import com.flipkart.handsonlearning.db.BookDAO;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by vaibhav.jain on 06/07/16.
 */
@Path("/Author")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorDAO AuthorDAO;
    private final BookDAO BookDAO;

    public AuthorResource(AuthorDAO AuthorDAO, BookDAO bookDAO) {
        this.AuthorDAO = AuthorDAO;
        this.BookDAO = bookDAO;
    }

    @POST
    @UnitOfWork
    public long addAAuthor(Author Author) {
        return AuthorDAO.add(Author);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Author> getAuthorById(@PathParam("id") LongParam AuthorId) {
        return AuthorDAO.findById(AuthorId.get());
    }

    @GET
    @UnitOfWork
    public List<Book> findBooksByAuthor(@QueryParam("name") Optional<String> name) {
        return BookDAO.findBooksByAuthor(name);
    }


}
