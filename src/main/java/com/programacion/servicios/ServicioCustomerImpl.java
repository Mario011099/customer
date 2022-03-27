package com.programacion.servicios;

import com.programacion.db.Customer;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ServicioCustomerImpl implements ServicioCustomer{

    @Inject
    private DataSource dataSource;


    public Customer findById(Integer id) {
        return null;
    }


    public List<Customer> findAll() {

        List<Customer> ret = new ArrayList<>();
        Connection con = null;
        Customer c;

        try{
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("MATCH (n:Customer) RETURN n");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                c = new Customer();
                c.setId(rs.getInt("n.id"));
                c.setName(rs.getString("n.name"));
                c.setSurname(rs.getString("n.surname"));

                ret.add(c);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
//        Session session = driver.session();
//        Result result = session.beginTransaction().run("MATCH (a:Customer) Return a");
//        for (Record r:result.list()) {
//            r.get(0);
//        }
//
        return ret;
    }


    public void delete(Integer id) {

    }


    public void update(Integer id, Customer obj) {

    }


    public void create(Customer c) {

    }
}
