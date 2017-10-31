package facades;

import entity.Role;
import entity.User;
import java.util.ArrayList;
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
        userFacade.addEntityManagerFactory(emf);
        roleFacade.addEntityManagerFactory(emf);
    }
    
    @Override
    public boolean registerUser(User user)
    {
        List<Role> roles = new ArrayList();
        for (Role role : user.getRoles())
        {
            if (roleFacade.findRole(role) != null)
                roles.add(role);
        }
        user.setRoles(roles);
        
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

    @Override
    public boolean createRole(Role role)
    {
        return roleFacade.createRole(role);
    }
    
}