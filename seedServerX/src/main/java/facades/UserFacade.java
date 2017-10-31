package facades;

import entity.Role;
import security.IUserFacade;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import security.IUser;
import security.PasswordStorage;

public class UserFacade implements IUserFacade {

    EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    @Override
    public IUser getUserByUserId(String id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(User.class, id);
        } finally
        {
            em.close();
        }
    }

    /*
  Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password)
    {
        try
        {
            System.out.println("User Before:" + userName + ", " + password);
            IUser user = getUserByUserId(userName);
            System.out.println("User After:" + user.getUserName() + ", " + user.getPasswordHash());
            return user != null && PasswordStorage.verifyPassword(password, user.getPasswordHash()) ? user.getRolesAsStrings() : null;
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex)
        {
            throw new NotAuthorizedException("Invalid username or password", Response.Status.FORBIDDEN);
        }
    }

    @Override
    public boolean registerUser(User user, List<Role> roles)
    {
        EntityManager em = getEntityManager();
        
        for (int i = 0; i < roles.size(); i++)
        {
            Role found = em.find(Role.class, roles.get(i));
            
            if (found != null)
            {
                user.addRole(found);
                roles.remove(i);
                i--;
            }
        }
        
        for (Role role : roles)
        {
            user.addRole(role);
            em.persist(role);
        }
        
        em.persist(user);
        em.close();
        
        return true;
    }

}
