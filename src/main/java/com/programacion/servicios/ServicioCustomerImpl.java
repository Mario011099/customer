package com.programacion.servicios;

import com.programacion.db.Customer;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import javax.inject.Inject;
import java.util.List;

public class ServicioCustomerImpl implements ServicioCustomer{

    @Inject
    private Driver driver;


    public Customer findById(Integer id) {
        return null;
    }


    public List<Customer> findAll() {
        Session session = driver.session();
        session.writeTransaction(tx -> {
            Result result = tx.run("MATCH (a:Customer) Return a");
        });
        return null;
    }


    public void delete(Integer id) {

    }


    public void update(Integer id, Customer obj) {

    }


    public void create(Customer c) {

    }
}
