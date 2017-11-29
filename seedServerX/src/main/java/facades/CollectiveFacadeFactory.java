package facades;

import java.util.HashMap;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CollectiveFacadeFactory {

    // Private constructor for a static class //
    private CollectiveFacadeFactory() {
    }

    private static final ICollectiveFacade INSTANCE = new CollectiveFacade(Persistence.createEntityManagerFactory("pu_development"),
            new UserFacade(), new RoleFacade(), new PlaceFacade(), new HomeFacade());

    public static ICollectiveFacade getInstance() {
        return INSTANCE;
    }

    public static ICollectiveFacade getTestInstance() {
        Properties props = new Properties();
        props.setProperty("javax.persistence.jdbc.url", "jdbc:mysql://165.227.151.92:3306/CA3_test?zeroDateTimeBehavior=convertToNull");
        props.put("javax.persistence.schema-generation.drop-script-source", "sql/drop_test.sql");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development", props);

        Properties props2 = new Properties();
        props2.setProperty("javax.persistence.jdbc.url", "jdbc:mysql://165.227.151.92:3306/CA3_test?zeroDateTimeBehavior=convertToNull");
        Persistence.generateSchema("pu_development", props2);
        return new CollectiveFacade(emf, new UserFacade(), new RoleFacade(), new PlaceFacade(), new HomeFacade());
    }
}
