package com.programacion.config;


import org.eclipse.microprofile.config.Config;
import org.neo4j.driver.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;

@ApplicationScoped
public class DatabaseConfig implements  AutoCloseable{

    @Inject private Config config;

    private Driver driver;

    @Override
    public void close() throws Exception {
        driver.close();
    }

    @Produces
    @ApplicationScoped
    public Driver driver(){
        String uri ="neo4j+s://c27ec571.databases.neo4j.io:7687";
        String user = "neo4j";
        String password="1lSGpxBwhkopaVVzsdysmepZAm7SYIh7kPwpF-yS7Rs";

        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );

        return driver;
    }
}