package facades;

import entity.IUser;
import entity.Place;
import entity.Role;
import entity.User;
import java.util.List;

public interface ICollectiveFacade 
{
    List<String> authenticateUser(String userName, String password);
    IUser getUserByUserId(String id);
    boolean registerUser(User user);
    List<User> listAllUsers();
    
    boolean createRole(Role role);
    boolean removeRole(String roleName);
    Role findRole(String roleName);
    
    boolean createPlace(Place place);
    boolean removePlace(int id);
    Place findPlace(int id);
    List<Place> listAllPlaces();
}
