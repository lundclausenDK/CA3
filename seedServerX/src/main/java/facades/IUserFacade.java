package facades;

import entity.User;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import entity.IUser;

interface IUserFacade 
{
    void addEntityManagerFactory(EntityManagerFactory emf);
    List<String> authenticateUser(String userName, String password);
    User findUser(String id);
    boolean registerUser(User user);
    boolean deleteUser(String username);
    boolean editUser(User user);
    List<User> listAllUsers();
}
