/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.UserService;


@Path("students")
@Stateless
public class UserResource {

    @Inject
    private UserService s;

    @GET
    public List<User> getAll() {
        return s.getUsers();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("name") String name) {
        User user = s.findByName(name);
        return user;
    }
}

//  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})