package com.programacion.servicios;

import com.programacion.db.Customer;

import java.util.List;

public interface ServicioCustomer {
    Customer findById(Integer id);
    List<Customer> findAll();
    void delete (Integer id);
    void update (Integer id, Customer obj);
    void create(Customer c);
}
