package com.programacion.config;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.programacion.db.Customer;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class CustomerDataFetcher {

    private final List<Customer> customers= List.of(
        new Customer(1,"N1","A1"),
            new Customer(2,"N2","A2"),
            new Customer(3,"N3","A3")
    );

    @DgsQuery
    public List<Customer> customers(@InputArgument Integer id){
        if(id == null) {
            return customers;
        }

        return customers.stream().filter(s -> s.getId().equals(id)).collect(Collectors.toList());
    }

}

