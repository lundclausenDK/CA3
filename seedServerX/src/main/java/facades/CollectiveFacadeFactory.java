package facades;

import javax.persistence.Persistence;

public class CollectiveFacadeFactory 
{
    // Private constructor for a static class //
    private CollectiveFacadeFactory(){}
    
    private static final ICollectiveFacade INSTANCE = new CollectiveFacade
        (Persistence.createEntityManagerFactory("pu_development"), new UserFacade(), new RoleFacade(), new PlaceFacade());

    public static ICollectiveFacade getInstance()
    {
        return INSTANCE;
    }
    
    public static ICollectiveFacade getTestInstance(String puName)
    {
        return new CollectiveFacade(Persistence.createEntityManagerFactory(puName), new UserFacade(), new RoleFacade(), new PlaceFacade());
    }
}