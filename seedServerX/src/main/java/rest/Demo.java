package rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
public class Demo {

    @Path("admin")
    @GET
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdmin() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"Hello Admin from server (call accesible by only authenticated ADMINS)\",\n" + "\"serverTime\": \"" + now + "\"}";
    }

    @Path("user")
    @GET
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser() {
        return "{\"message\" : \"Hello User from Server (Accesible by only authenticated USERS)\"}";
    }

    @Path("demoall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNonuser() {
        return " {\"message\" : \"result for all\"}";
    }

}
