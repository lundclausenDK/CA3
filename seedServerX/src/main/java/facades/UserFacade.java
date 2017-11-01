package facades;

import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import entity.IUser;
import security.PasswordStorage;

class UserFacade implements IUserFacade {

    EntityManagerFactory emf;

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
    public boolean registerUser(User user)
    {
        EntityManager em = getEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(user);
        
        em.getTransaction().commit();
        em.close();
        
        return true;
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

}
