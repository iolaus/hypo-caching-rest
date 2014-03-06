package de.hypoport.caching.dao;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import de.hypoport.caching.rest.User;
import java.net.UnknownHostException;

public class MongoProvider {

  private static final String Versicherung_Phv_DB_NAME_PROD = "hypocache";
  private static final String Versicherung_Phv_DB_NAME_TEST = "hypocache-test";
  private final static int MONGODB_PORT = 27000;
  private static MongoProvider instance;

  private Mongo mongoQA;
  private final Morphia morphia;

  private MongoProvider() throws UnknownHostException {

    this.morphia = new Morphia();
    this.morphia.map(User.class);
  }

  public static MongoProvider instance() throws UnknownHostException {
    if (instance == null) {
      synchronized (MongoProvider.class) {
        if (instance == null) {
          instance = new MongoProvider();
        }
      }
    }
    return instance;
  }

  public Datastore createQADB() throws UnknownHostException {
    createMongoQaIfNotExists();
    final Datastore ds = morphia.createDatastore(mongoQA, Versicherung_Phv_DB_NAME_PROD);
    ds.ensureIndexes();
    ds.ensureCaps();

    return ds;
  }

  public Datastore createTestDB() throws UnknownHostException {
    createMongoQaIfNotExists();
    final Datastore ds = morphia.createDatastore(mongoQA, Versicherung_Phv_DB_NAME_TEST);
    ds.ensureIndexes();
    ds.ensureCaps();
    return ds;
  }

  public void dropTestDatabase() {
    mongoQA.dropDatabase(Versicherung_Phv_DB_NAME_TEST);
  }

  private void createMongoQaIfNotExists() throws UnknownHostException {
    if (this.mongoQA == null) {
      this.mongoQA = new MongoClient("192.168.99.125", MONGODB_PORT);
    }
  }

}
