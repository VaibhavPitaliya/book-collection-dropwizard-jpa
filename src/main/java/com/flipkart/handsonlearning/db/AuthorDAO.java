package com.flipkart.handsonlearning.db;

import com.flipkart.handsonlearning.core.Author;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by vaibhav.jain on 06/07/16.
 */
public class AuthorDAO extends AbstractDAO<Author> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public AuthorDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }



    /**
     * Method returns all the Authors stored in the database.
     */
    public List<Author> findAll() {
        return list(namedQuery("find_all_Authors"));
    }


    public long add(Author Author) {
        return persist(Author).getId();
    }

    /**
     * Method looks for an employee by her authorId.
     *
     * @param id the authorId of an employee we are looking for.
     * @return Optional containing the found employee or an empty Optional
     * otherwise.
     */
    public Optional<Author> findById(long id) {
        return Optional.fromNullable(get(id));
    }

}
