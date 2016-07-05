package com.flipkart.handsonlearning.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.Objects;

/**
 * Created by vaibhav.jain on 05/07/16.
 */

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(
                name = "find_all_books",
                query = "SELECT b FROM Book b"
        ),
        @NamedQuery(
                name = "find_book_by_id",
                query = "SELECT b FROM Book b"
                + "where b.id like :id ")
//        @NamedQuery(
//                name = "find_books_by_author",
//                query = "SELECT b FROM Book b, Author a, Book_Author ba"
//                + "where a.name like :name")
})

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("BOOK_ID")
    private long id;

    @Column(name = "ISBN", nullable = false)
    private long isbn;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="book_author",
            joinColumns={@JoinColumn(name="BOOK_ID", referencedColumnName="BOOK_ID")},
            inverseJoinColumns={@JoinColumn(name="AUTHOR_ID", referencedColumnName="AUTHOR_ID")})
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
