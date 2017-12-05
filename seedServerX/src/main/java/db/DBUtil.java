package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class DBUtil {

    private EntityManagerFactory emf;

    public DBUtil(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void clearDB() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Query ignoreKey = em.createNativeQuery("SET foreign_key_checks = 0;");

        Query[] deletes = {
            em.createNativeQuery("truncate table HOME;"),
            em.createNativeQuery("truncate table BOOKING;"),
            em.createNativeQuery("truncate table SEED_USER;"),
            em.createNativeQuery("truncate table SEED_USER_USER_ROLE;"),
            em.createNativeQuery("truncate table PLACE;"),
            em.createNativeQuery("truncate table PLACE_RATING;"),
            em.createNativeQuery("truncate table RATING;"),
            em.createNativeQuery("truncate table USER_ROLE;"),
            em.createNativeQuery("truncate table HOME_BOOKING;")
        };

        Query acceptKey = em.createNativeQuery("SET foreign_key_checks = 1;");

        ignoreKey.executeUpdate();

        for (Query q : deletes) {
            q.executeUpdate();
        }

        acceptKey.executeUpdate();

        em.getTransaction().commit();
        em.close();
    }
}
