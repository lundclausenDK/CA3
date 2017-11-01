package rest;

import com.google.gson.Gson;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entity.User;

@Path("users")
@RolesAllowed("Admin")
public class Users {

    ICollectiveFacade icf = CollectiveFacadeFactory.getInstance();
    Gson gs = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        List<User> ul = icf.listAllUsers();
        String output = "[";
        for (int i = 0; i < ul.size(); i++) {
            if (i == ul.size() - 1) {
                output += "\"" + ul.get(i).getUserName() + "\"";
            } else {
                output += "\""+ ul.get(i).getUserName() + "\",";
            }
        }
        output += "]";
        return output;
    }
}
