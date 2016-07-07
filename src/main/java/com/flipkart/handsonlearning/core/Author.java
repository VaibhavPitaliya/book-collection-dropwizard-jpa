package com.flipkart.handsonlearning.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaibhav.jain on 05/07/16.
 */



@NamedQueries({
        @NamedQuery(
                name = "find_all_authors",
                query = "SELECT b FROM Author b"
        )
})
@Entity
@Table(name = "author")
@Getter
@Setter
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;

    @Column(name = "author_name", nullable = false)
    @JsonProperty("name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "author", fetch = FetchType.EAGER)
    private List<> book;
}
