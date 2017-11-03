package rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Role;
import facades.CollectiveFacadeFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import security.PasswordStorage;

@Path("user_control")
@RolesAllowed("Admin")
public class UserControl {

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUser(String body)
    {
        String status = "User does not exists";
        JsonObject json = new JsonParser().parse(body).getAsJsonObject();
        String username = json.get("username").getAsString();

        boolean success = CollectiveFacadeFactory.getInstance().deleteUser(username);

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
            boolean success = CollectiveFacadeFactory.getInstance().registerUser(registrant);

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
        boolean success = CollectiveFacadeFactory.getInstance().editUser(registrant);

        if (success)
        {
            status = "Successfully edited user.";
        }

        return "{\"status\": \"" + status + "\"}";
    }
}
