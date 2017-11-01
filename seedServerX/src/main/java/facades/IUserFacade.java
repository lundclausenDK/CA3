package facades;

import entity.User;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import entity.IUser;

interface IUserFacade 
{
    void addEntityManagerFactory(EntityManagerFactory emf);
    List<String> authenticateUser(String userName, String password);
    IUser getUserByUserId(String id);
    boolean registerUser(User user);
    List<User> listAllUsers();
}
