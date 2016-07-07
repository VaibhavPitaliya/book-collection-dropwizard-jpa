package com.flipkart.handsonlearning;

import com.flipkart.handsonlearning.core.Author;
import com.flipkart.handsonlearning.core.Book;
import com.flipkart.handsonlearning.db.AuthorDAO;
import com.flipkart.handsonlearning.db.BookDAO;
import com.flipkart.handsonlearning.resources.AuthorResource;
import com.flipkart.handsonlearning.resources.BookResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by vaibhav.jain on 06/07/16.
 */
public class BookCollectionApplication extends Application<BookCollectionConfiguration> {
    public static void main(String[] args) throws Exception {
        new BookCollectionApplication().run(args);
    }

    private final HibernateBundle<BookCollectionConfiguration> hibernateBundle =
            new HibernateBundle<BookCollectionConfiguration>(Book.class, Author.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(BookCollectionConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };



    @Override
    public void initialize(Bootstrap<BookCollectionConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.addBundle(hibernateBundle);

    }

    @Override
    public void run(BookCollectionConfiguration configuration, Environment environment) {
        final BookDAO bookDAO = new BookDAO(hibernateBundle.getSessionFactory());
        final AuthorDAO authorDAO = new AuthorDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new BookResource(bookDAO));
        environment.jersey().register(new AuthorResource(authorDAO));
    }
}
