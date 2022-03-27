package com.programacion.servicios;

import com.programacion.db.Customer;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioCustomerImpl implements ServicioCustomer {

    @Inject
    private Driver driver;


    public Customer findById(Integer id) {
        Customer c;
        String query = String.format("MATCH (a:Customer{id:%s})\n " +
                "RETURN a.id as id, a.name as name, a.surname as surname\n",id);

        Session session = driver.session();

        Record record = session.readTransaction(tx -> {
            Result result = tx.run(query);
            return result.single();

        });
        c=new Customer();
        c.setId(record.get("id").asInt());
        c.setName(record.get("name").asString());
        c.setSurname(record.get("surname").asString());
        return c;
    }


    public List<Customer> findAll() {

        List<Customer> ret = new ArrayList<>();
        Customer c;
        String query = "MATCH (a:Customer)\n " +
                "RETURN a.id as id, a.name as name, a.surname as surname\n";

        Session session = driver.session();

        List<Record> records = session.readTransaction(tx -> {
            Result result = tx.run(query);
            return result.list();


        });


        for (Record r : records) {
            c = new Customer();
            c.setId(r.get("id").asInt());
            c.setName(r.get("name").asString());
            c.setSurname(r.get("surname").asString());

            ret.add(c);
        }

        session.close();

        return ret;
    }


    public void delete(Integer id) {
        String query = String.format("MATCH (a:Customer{id:%s})\n " +
                "DETACH DELETE a\n",id);

        Session session = driver.session();
        session.run(query);
        session.close();
    }


    public void update(Integer id, Customer obj) {

    }


    public void create(Customer c) {
        Integer id = c.getId();
        String name = c.getName();
        String surname = c.getSurname();
        String query = String.format("CREATE (n:Customer {id: %s, name: '%s', surname: '%s'})", id, name, surname);

        Session session = driver.session();
        session.run(query);
        session.close();


    }
}
