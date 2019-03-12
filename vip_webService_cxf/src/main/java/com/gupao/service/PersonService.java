package com.gupao.service;


import com.gupao.po.Person;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@WebService
@Path(value="/users/")
public interface PersonService {

        @GET
        @Path("/")  //http://ip:port/users
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public List<Person> getUsers();

        @DELETE
        @Path("{id}")  //http://ip:port/users/1
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
            //请求accept
        Response delete(@PathParam("id") int id);

        @GET
        @Path("{id}") //http://ip:port/users/1
        @Produces(MediaType.APPLICATION_JSON)
        Person getUser(@PathParam("id") int id);

        @POST
        @Path("add")
        Response insert(Person user);

        @PUT
        @Path("update")
        Response update(Person user);
}
