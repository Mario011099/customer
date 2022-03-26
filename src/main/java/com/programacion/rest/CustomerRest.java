package com.programacion.rest;

import com.programacion.db.Customer;
import com.programacion.servicios.ServicioCustomer;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/customers")
public class CustomerRest {

    @Inject
    ServicioCustomer servicio;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById (@PathParam("id")Integer id){
        Customer c = servicio.findById(id);

        if (c == null){
            return Response.status(Response.Status.NOT_FOUND).build();

        }

        return Response.ok(c).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAll(){
        return servicio.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertar(Customer c){
        servicio.create(c);
        return  Response.status(Response.Status.CREATED).build();
    }

    @DELETE @Path("/{id}")
    public Response delete (@PathParam("id") Integer id){
        servicio.delete(id);

        return Response.status((Response.Status.OK) ).build();
    }

    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar (Customer c, @PathParam("id") Integer id){
        servicio.update(id,c);
        return Response.status((Response.Status.OK) ).build();
    }

}
