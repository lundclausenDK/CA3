package facades;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CollectiveFacadeFactory {

    // Private constructor for a static class //
    private CollectiveFacadeFactory()
    {
    }

    private static final ICollectiveFacade INSTANCE = new CollectiveFacade(Persistence.createEntityManagerFactory("pu_development"), 
            new UserFacade(), new RoleFacade(), new PlaceFacade());

    public static ICollectiveFacade getInstance()
    {
        return INSTANCE;
    }

    public static ICollectiveFacade getTestInstance()
    {
        Properties props = new Properties();
        props.setProperty("javax.persistence.jdbc.url", "jdbc:mysql://165.227.151.92:3306/CA3_test?zeroDateTimeBehavior=convertToNull");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development", props);
        return new CollectiveFacade(emf, new UserFacade(), new RoleFacade(), new PlaceFacade());
    }
}
