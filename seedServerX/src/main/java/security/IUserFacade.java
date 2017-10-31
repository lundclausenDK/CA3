package security;

import entity.Role;
import entity.User;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author lam
 */
public interface IUserFacade {

    /*
    Return the Roles if users could be authenticated, otherwise null
     */
    List<String> authenticateUser(String userName, String password);
    IUser getUserByUserId(String id);
    boolean registerUser(User user);
    void addEntityManagerFactory(EntityManagerFactory emf);
}
