package com.flipkart.handsonlearning.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by vaibhav.jain on 05/07/16.
 */


@Getter
@Setter
@NamedQueries({
        @NamedQuery(
                name = "find_all_authors",
                query = "SELECT b FROM Author b"
        )
})
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("author_id")
    @Column(name = "author_id")
    private long id;

    @Column(name = "author_name", nullable = false)
    @JsonProperty("name")
    private String name;

}
