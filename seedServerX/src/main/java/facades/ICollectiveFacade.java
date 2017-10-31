package facades;

import entity.Role;
import entity.User;
import java.util.List;
import security.IUser;

public interface ICollectiveFacade {

    boolean registerUser(User user, List<Role> roles);
    List<String> authenticateUser(String userName, String password);
    IUser getUserByUserId(String id);
}
