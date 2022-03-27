package com.programacion.servicios;

import com.programacion.db.Customer;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ServicioCustomerImpl implements ServicioCustomer{

    @Inject
    private Driver driver;


    public Customer findById(Integer id) {
        return null;
    }


    public List<Customer> findAll() {
        List<Customer> ret = null;
        Session session = driver.session();
        Result result = session.beginTransaction().run("MATCH (a:Customer) Return a");
        for (Record r:result.list()) {
            r.get(0);
        }

        return ret;
    }


    public void delete(Integer id) {

    }


    public void update(Integer id, Customer obj) {

    }


    public void create(Customer c) {

    }
}
