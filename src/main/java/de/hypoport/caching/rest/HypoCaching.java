/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author steffen.kaempke
 */
@Path("users")
public class HypoCaching {
  
  private final List<User> users = new ArrayList<User>();

  @GET
  @Produces(APPLICATION_JSON)
  public List<User> getAll() {
    return users;
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response putUser(User user) {
    this.users.add(user);
    return Response.ok().build();
  }

  @GET
  @Path("{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUser(@PathParam("name") String name) {
    final User user =  new User(name);
    int index = users.indexOf(user);
    
    if ( index != -1) {
      return users.get(index);
    } else {
      return null;
    }
    
  }

}
