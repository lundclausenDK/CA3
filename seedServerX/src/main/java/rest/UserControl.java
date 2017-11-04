package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Role;
import entity.User;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import security.PasswordStorage;

@Path("user_control")
@RolesAllowed("Admin")
public class UserControl {

    private ICollectiveFacade facade = CollectiveFacadeFactory.getInstance();

    @GET
    @Path("list_roles")
    public String listAllRoles()
    {
        List<Role> res = facade.listAllRoles();
        String[] roles = new String[res.size()];
        for (int i = 0; i < res.size(); i++)
        {
            roles[i] = res.get(i).getRoleName();
        }
        return new Gson().toJson(roles);
    }

    @GET
    @Path("list_users")
    public String listAllUsers()
    {
        List<User> res = facade.listAllUsers();
        JsonObject[] jsonObjects = new JsonObject[res.size()];
        
        for (int i = 0; i < res.size(); i++)
        {
            JsonObject obj = new JsonObject();
            obj.addProperty("username", res.get(i).getUserName());
            JsonArray roles = new JsonArray();
            
            for (Role role : res.get(i).getRoles())
            {
                roles.add(role.getRoleName());
            }
            obj.add("roles", roles);
            
            jsonObjects[i] = obj;
        }
        
        return new Gson().toJson(jsonObjects);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUser(String body)
    {
        String status = "User does not exists";
        JsonObject json = new JsonParser().parse(body).getAsJsonObject();
        String username = json.get("username").getAsString();

        boolean success = facade.deleteUser(username);

        if (success)
        {
            status = "Successfully deleted user.";
        }

        return "{\"status\": \"" + status + "\"}";
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(String body)
    {
        String status = "Could not add user";
        JsonObject json = new JsonParser().parse(body).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        JsonArray roles = json.get("roles").getAsJsonArray();

        List<Role> foundRoles = new ArrayList();
        for (JsonElement role : roles)
        {
            foundRoles.add(new Role(role.getAsString()));
        }

        try
        {
            entity.User registrant = new entity.User(username, password, foundRoles);
            boolean success = facade.registerUser(registrant);

            if (success)
            {
                status = "Successfully added user.";
            }
        }
        catch (PasswordStorage.CannotPerformOperationException ex)
        {
            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "{\"status\": \"" + status + "\"}";
    }

    @PUT
    @Path("edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editUser(String body)
    {
        String status = "Could not edit user";
        JsonObject json = new JsonParser().parse(body).getAsJsonObject();
        String username = json.get("username").getAsString();
        JsonArray roles = json.get("roles").getAsJsonArray();

        List<Role> foundRoles = new ArrayList();
        for (JsonElement role : roles)
        {
            foundRoles.add(new Role(role.getAsString()));
        }

        entity.User registrant = new entity.User(username, foundRoles);
        boolean success = facade.editUser(registrant);

        if (success)
        {
            status = "Successfully edited user.";
        }

        return "{\"status\": \"" + status + "\"}";
    }
}
