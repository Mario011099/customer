package com.programacion.config;


import org.eclipse.microprofile.config.Config;
import org.neo4j.driver.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.sql.SQLException;

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
    public Driver driver()  {
        String uri = config.getValue("uri",String.class);

        String user = config.getValue("user",String.class);

        String password= config.getValue("password", String.class);

        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ), org.neo4j.driver.Config.defaultConfig());

        return driver;
    }
}
