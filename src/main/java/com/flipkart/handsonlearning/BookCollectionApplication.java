package com.flipkart.handsonlearning;

import com.flipkart.handsonlearning.core.Book;
import com.flipkart.handsonlearning.db.BookDAO;
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
            new HibernateBundle<BookCollectionConfiguration>(Book.class) {
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
        final BookDAO dao = new BookDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new BookResource(dao));
    }
}
