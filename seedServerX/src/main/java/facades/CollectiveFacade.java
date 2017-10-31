package facades;

import entity.Role;
import entity.User;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import security.IUser;
import security.IUserFacade;

public class CollectiveFacade implements ICollectiveFacade
{
    private final IUserFacade userFacade;
    private final IRoleFacade roleFacade;

    public CollectiveFacade(EntityManagerFactory emf, IUserFacade userFacade, IRoleFacade roleFacade)
    {
        this.userFacade = userFacade;
        this.roleFacade = roleFacade;
    }
    
    @Override
    public boolean registerUser(User user, List<Role> roles)
    {
        for (Role role : roles)
        {
            user.addRole(roleFacade.createRole(role));
        }
        
        return userFacade.registerUser(user);
    }
    
    @Override
    public List<String> authenticateUser(String userName, String password)
    {
        return userFacade.authenticateUser(userName, password);
    }
    
    @Override
    public IUser getUserByUserId(String id)
    {
        return userFacade.getUserByUserId(id);
    }
    
}