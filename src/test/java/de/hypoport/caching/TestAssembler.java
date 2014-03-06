/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hypoport.caching;

import com.google.code.morphia.Datastore;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import de.hypoport.caching.dao.IUserDao;
import de.hypoport.caching.dao.MongoProvider;
import de.hypoport.caching.dao.UserDao;
import de.hypoport.caching.guice.HypoCachingAssembler;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author steffen.kaempke
 */
public class TestAssembler extends AbstractModule{
  @Override
  protected void configure() {
     bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
     bind(IUserDao.class).to(UserDao.class);
    try {
      bind(Datastore.class).toInstance(MongoProvider.instance().createTestDB());
    } catch (UnknownHostException ex) {
      Logger.getLogger(HypoCachingAssembler.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
