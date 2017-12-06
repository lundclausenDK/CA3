package facades;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CollectiveFacadeFactory {

    // Private constructor for a static class //
    private CollectiveFacadeFactory() {
    }

    private static final ICollectiveFacade INSTANCE = new CollectiveFacade(Persistence.createEntityManagerFactory("pu_development"),
            new UserFacade(), new RoleFacade(), new PlaceFacade(), new HomeFacade());

    private static EntityManagerFactory testEmf;

    public static ICollectiveFacade getInstance() {
        return INSTANCE;
    }

    public static ICollectiveFacade getTestInstance() {
        Properties props = new Properties();
        props.setProperty("javax.persistence.jdbc.url", "jdbc:mysql://165.227.151.92:3306/CA3_test?zeroDateTimeBehavior=convertToNull");
        props.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development", props);

        testEmf = emf;
        Persistence.generateSchema("pu_development", props); // clears and updates the tables

        return new CollectiveFacade(emf, new UserFacade(), new RoleFacade(), new PlaceFacade(), new HomeFacade());
    }

    public static EntityManagerFactory getTestEmf() {
        return testEmf;
    }
}
