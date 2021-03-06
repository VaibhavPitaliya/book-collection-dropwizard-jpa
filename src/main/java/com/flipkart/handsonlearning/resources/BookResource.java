package com.flipkart.handsonlearning.resources;


import com.flipkart.handsonlearning.core.Book;
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
@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookDAO bookDAO;

    public BookResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @POST
    @UnitOfWork
    @Produces("application/json")
    public Book addABook(Book book) {
        return bookDAO.add(book);
    }

    @GET
    @UnitOfWork
    public List<Book> listOfBooks() {
        return bookDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Book> getBookById(@PathParam("id") LongParam bookId) {
        return bookDAO.findById(bookId.get());
    }

    @GET
    @UnitOfWork
    @Path("/author")
    public List<Book> findBooksByAuthor(@QueryParam("name") String name) {
        return bookDAO.findBooksByAuthor(name);
    }
}
