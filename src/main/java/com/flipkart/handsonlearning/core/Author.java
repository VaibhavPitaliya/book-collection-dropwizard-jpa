package com.flipkart.handsonlearning.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.List;

/**
 * Created by vaibhav.jain on 05/07/16.
 */

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("AUTHOR_ID")
    private long id;

    @Column(name = "AUTHOR_NAME", nullable = false)
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="book_author",
            joinColumns={@JoinColumn(name="AUTHOR_ID", referencedColumnName="AUTHOR_ID")},
            inverseJoinColumns={@JoinColumn(name="BOOK_ID", referencedColumnName="BOOK_ID")})
    private List<Book> bookList;
}
