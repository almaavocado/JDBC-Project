package csulb.cecs323.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * Modeled to follow the TinyHomesApp
 * Resource Used: Dr. Monge's JPAStarterApp
 * *Please find under app folder*
 */

public class TinyHomesApp {
   private static final String DERBY_PERSISTENCE_UNIT_NAME = "TinyHomes_PU_Derby";
   private static final String MYSQL_PERSISTENCE_UNIT_NAME = "TinyHomes_PU_MySQL";
   private static final String MYSQL_SERVER_PERSISTENCE_UNIT_NAME = "TinyHomes_PU_MySQL_Server";
   private static final Logger LOGGER = Logger.getLogger(TinyHomesApp.class.getName());

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory(MYSQL_SERVER_PERSISTENCE_UNIT_NAME);
      EntityManager manager = factory.createEntityManager();

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      Menu menu = new Menu(manager, tx);
      menu.mainMenu();

      LOGGER.fine("End of Transaction");
   }
}
