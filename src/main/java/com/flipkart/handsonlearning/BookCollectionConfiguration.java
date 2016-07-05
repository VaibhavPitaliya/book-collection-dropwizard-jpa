package com.flipkart.handsonlearning;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by vaibhav.jain on 05/07/16.
 */
public class BookCollectionConfiguration extends Configuration {

        @Valid
        @NotNull
        private DataSourceFactory database = new DataSourceFactory();

        @JsonProperty("database")
        public DataSourceFactory getDataSourceFactory() {
            return database;
        }
}



