package com.flipkart.handsonlearning.core;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
                query = "SELECT b FROM Book b where b.id = :id"
                 ),
        @NamedQuery(
                name = "find_books_by_author",
                query = "SELECT b FROM Book b, Author a where b.author = a.id and a.name LIKE :name"
        )
})
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @Column(name = "isbn", nullable = false)
    private long isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

//    @OneToOne(cascade=CascadeType.ALL)
//    @JoinTable(name="book_author",
//            joinColumns={@JoinColumn(name="BOOK_ID", referencedColumnName="BOOK_ID")},
//            inverseJoinColumns={@JoinColumn(name="AUTHOR_ID", referencedColumnName="AUTHOR_ID")})
//    private Author author;
}
