package com.flipkart.handsonlearning.resources;


import com.flipkart.handsonlearning.core.Book;
import com.flipkart.handsonlearning.db.BookDAO;
import com.google.common.base.Optional;
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
    public long addABook(Book book) {
        return bookDAO.add(book);
    }

    @GET
    public List<Book> listOfBooks() {
        return bookDAO.findAll();
    }

    @GET
    @Path("/{bookId}")

    public Optional<Book> getBookById(@PathParam("bookId") LongParam bookId) {
        return bookDAO.findById(bookId.get());
    }
}
