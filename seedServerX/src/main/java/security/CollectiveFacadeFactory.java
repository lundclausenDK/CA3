package security;

import facades.CollectiveFacade;
import facades.ICollectiveFacade;
import facades.RoleFacade;
import facades.UserFacade;
import javax.persistence.Persistence;

/**
 *
 * @author lam
 */
public class CollectiveFacadeFactory 
{
//    private static final IUserFacade instance = 
//            new UserFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
private static final ICollectiveFacade instance = 
        new CollectiveFacade(Persistence.createEntityManagerFactory("pu_development"), new UserFacade(), new RoleFacade());
    public static ICollectiveFacade getInstance(){
        return instance;
    }
}