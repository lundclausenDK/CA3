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

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public User findUser(String id) {
        EntityManager em = getEntityManager();

        User found = em.find(User.class, id);
        em.close();

        return found;
    }

    /*
        Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password) {
        try {
            System.out.println("User Before:" + userName + ", " + password);
            IUser user = findUser(userName);
            System.out.println("User After:" + user.getUserName() + ", " + user.getPasswordHash());
            return user != null && PasswordStorage.verifyPassword(password, user.getPasswordHash()) ? user.getRolesAsStrings() : null;
        }
        catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            throw new NotAuthorizedException("Invalid username or password", Response.Status.FORBIDDEN);
        }
    }

    @Override
    public boolean registerUser(User user) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.persist(user);

        em.getTransaction().commit();
        em.close();

        return true;
    }

    @Override
    public boolean deleteUser(String username) {
        EntityManager em = emf.createEntityManager();

        User found = em.find(User.class, username);

        if (found == null) {
            return false;
        }

        em.getTransaction().begin();
        em.remove(found);
        em.getTransaction().commit();
        em.close();

        return true;
    }

    @Override
    public boolean editUser(User user) {
        EntityManager em = emf.createEntityManager();

        User found = em.find(User.class, user.getUserName());

        if (found == null) {
            return false;
        }

        em.getTransaction().begin();
        found.setRoles(user.getRoles());
        em.persist(found);
        em.getTransaction().commit();
        em.close();

        return true;
    }

    @Override
    public List<User> listAllUsers() {
        EntityManager em = getEntityManager();

        List<User> res = em.createQuery("select u from SEED_USER u").getResultList();
        em.close();

        return res;
    }

}
