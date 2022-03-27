package com.programacion.graphql;

import com.programacion.db.Customer;
import com.programacion.servicios.ServicioCustomer;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class CustomerController {

    @Inject
    ServicioCustomer servicio;

    @Query("customers")
    @Description("Get all cutomers")
    public List<Customer> findAll(){
        return servicio.findAll();
    }

    @Query("customerById")
    @Description("Get customer By Id")
    public Customer findById(Integer id){
        return servicio.findById(id);
    }

    @Mutation("createCustomer")
    @Description("Create a Customer")
    public  Customer create (Customer c){
        servicio.create(c);
        return servicio.findById(c.getId());
    }

    @Mutation("modifyCustomer")
    @Description("Modify a Customer")
    public Customer update (Integer id, Customer c){
        servicio.update(id, c);
        return servicio.findById(id);
    }

    @Mutation("deleteCustomer")
    @Description("Delete a Customer")
    public Customer delete (Integer id){
        servicio.delete(id);
        return servicio.findById(id);
    }

}
