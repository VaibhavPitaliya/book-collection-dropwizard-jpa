package com.flipkart.handsonlearning.core;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
                query = "SELECT b FROM Book b, Author a where b.author = a.id and a.name LIKE :authorname"
        )
})
@Getter
@Setter
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @Column(name = "isbn", nullable = false)
    private long isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
//    @OneToOne(cascade=CascadeType.ALL)
//    @JoinTable(name="book_author",
//            joinColumns={@JoinColumn(name="BOOK_ID", referencedColumnName="BOOK_ID")},
//            inverseJoinColumns={@JoinColumn(name="AUTHOR_ID", referencedColumnName="AUTHOR_ID")})
//    private Author author;
}
