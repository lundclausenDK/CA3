package facades;

import entity.Booking;
import entity.Home;
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
    boolean deleteUser(String username);
    boolean editUser(User user);
    List<User> listAllUsers();
    
    boolean createRole(Role role);
    boolean removeRole(String roleName);
    Role findRole(String roleName);
    List<Role> listAllRoles();
    
    boolean createPlace(Place place);
    boolean removePlace(int id);
    Place findPlace(int id);
    Place findPlaceByName(String locationName);
    List<Place> listAllPlaces();
    List<Place> searchForPlaces(String searchWord);
    void addRating(int locationID, int rating, String userName);
    
    public List<Home> listAllHomes();
    public List<Home> findHomesCloseTo(String geolocation, double radius);
    public void addHome(Home home);
    public void bookHome(int id, Booking booking);
}
