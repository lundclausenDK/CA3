package facades;

import entity.Role;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import entity.IUser;
import entity.Place;

class CollectiveFacade implements ICollectiveFacade {

    private final IUserFacade userFacade;
    private final IRoleFacade roleFacade;
    private final IPlaceFacade placeFacade;

    CollectiveFacade(EntityManagerFactory emf, IUserFacade userFacade, IRoleFacade roleFacade, IPlaceFacade placeFacade)
    {
        this.userFacade = userFacade;
        this.roleFacade = roleFacade;
        this.placeFacade = placeFacade;
        userFacade.addEntityManagerFactory(emf);
        roleFacade.addEntityManagerFactory(emf);
        placeFacade.addEntityManagerFactory(emf);
    }

    // ####################### //
    // ##### User facade ##### //
    // ####################### //
    
    @Override
    public boolean registerUser(User user)
    {
        List<Role> roles = new ArrayList();
        for (Role role : user.getRoles())
        {
            if (roleFacade.findRole(role.getRoleName()) != null)
            {
                roles.add(role);
            }
        }
        user.setRoles(roles);

        return userFacade.registerUser(user);
    }

    @Override
    public boolean deleteUser(String username)
    {
        return userFacade.deleteUser(username);
    }

    @Override
    public boolean editUser(User user)
    {
        return userFacade.editUser(user);
    }

    @Override
    public List<String> authenticateUser(String userName, String password)
    {
        return userFacade.authenticateUser(userName, password);
    }

    @Override
    public IUser getUserByUserId(String id)
    {
        return userFacade.findUser(id);
    }

    @Override
    public List<User> listAllUsers()
    {
        return userFacade.listAllUsers();
    }

    // ####################### //
    // ##### Role Facade ##### //
    // ####################### //
    
    @Override
    public boolean createRole(Role role)
    {
        return roleFacade.createRole(role);
    }

    @Override
    public boolean removeRole(String roleName)
    {
        return roleFacade.removeRole(roleName);
    }

    @Override
    public Role findRole(String roleName)
    {
        return roleFacade.findRole(roleName);
    }

    @Override
    public List<Role> listAllRoles()
    {
        return roleFacade.listAllRoles();
    }

    // ######################## //
    // ##### Place Facade ##### //
    // ######################## //
    
    @Override
    public boolean createPlace(Place place)
    {
        return placeFacade.createPlace(place);
    }

    @Override
    public boolean removePlace(int id)
    {
        return placeFacade.removePlace(id);
    }

    @Override
    public Place findPlace(int id)
    {
        return placeFacade.findPlace(id);
    }

    @Override
    public List<Place> listAllPlaces()
    {
        return placeFacade.listAllPlaces();
    }

    @Override
    public List<Place> searchForPlaces(String searchWord)
    {
        return placeFacade.searchForPlaces(searchWord);
    }

    @Override
    public void addRating(int locationID, int rating, String userName)
    {
        placeFacade.addRating(locationID, rating, userName);
    }
}
