/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.rest;

import de.hypoport.caching.dao.IUserDao;
import java.util.List;
import javax.inject.Inject;
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

  @Inject
  private IUserDao dao;

  @GET
  @Produces(APPLICATION_JSON)
  public List<User> getAll() {
    return dao.all();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response save(User user) {
    dao.save(user);
    return Response.ok().build();
  }

  @GET
  @Path("{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public User read(@PathParam("name") String name) {
    return dao.read(name);

  }

}
