package com.flipkart.handsonlearning.resources;

import com.flipkart.handsonlearning.core.Author;
import com.flipkart.handsonlearning.db.AuthorDAO;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by vaibhav.jain on 06/07/16.
 */
@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorDAO authorDAO;

    public AuthorResource(AuthorDAO AuthorDAO) {
        this.authorDAO = AuthorDAO;
    }

    @POST
    @UnitOfWork
    public long addAAuthor(Author Author) {
        return authorDAO.add(Author);
    }

    @GET
    @Path("/all")
    @UnitOfWork
    public List<Author> listOfAuthors() {
        return authorDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Author> getAuthorById(@PathParam("id") LongParam AuthorId) {
        return authorDAO.findById(AuthorId.get());
    }
}
