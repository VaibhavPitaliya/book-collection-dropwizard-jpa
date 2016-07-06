/*
 * The MIT License
 *
 * Copyright 2015 Dmitry Noranovich.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.flipkart.handsonlearning.db;

import com.flipkart.handsonlearning.core.Book;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by vaibhav.jain on 05/07/16.
 */
public class BookDAO extends AbstractDAO<Book> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */

    private SessionFactory sessionFactory;

    @Inject
    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }


    /**
     * Method returns all the books stored in the database.
     */
    public List<Book> findAll() {
        return list(namedQuery("find_all_books"));
    }

    public List<Book> findBooksByAuthor(String name) {
        return list(namedQuery("find_books_by_author").setParameter(name,"CHIKU"));
    }

    public Book add(Book book) {
        persist(book);
        System.out.println(book.getAuthor().getName());
        return book;
    }

    /**
     * Method looks for an employee by her authorId.
     *
     * @param id the authorId of an employee we are looking for.
     * @return Optional containing the found employee or an empty Optional
     * otherwise.
     */
    public Optional<Book> findById(long id) {
        return Optional.fromNullable(get(id));
    }

}
