/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hypoport.caching.dao;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import de.hypoport.caching.rest.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author steffen.kaempke
 */
public class UserDao implements IUserDao {

  @Inject
  private Datastore ds;

  public UserDao() {

  }

  @Override
  public List<User> all() {
    return ds.find(User.class).asList();
  }

  @Override
  public User read(String imei) {
    return ds.find(User.class).field("imei").equal(imei).get();
  }

  @Override
  public void save(User user) {
    ds.save(user);
  }

  @Override
  public void delete(String imei) {
    Query<User> q = ds.createQuery(User.class);
    ds.delete(q.field("imei").equal(imei));
  }

  @Override
  public void removeAllUser() {
    Query<User> q = ds.createQuery(User.class);
    ds.getDB().dropDatabase();
  }
}
