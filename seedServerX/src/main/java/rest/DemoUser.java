package rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
public class DemoUser {

    @GET
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        return "{\"message\" : \"Hello User from Server (Accesible by only authenticated USERS)\"}";
    }

    @Path("demoall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return " {\"message\" : \"result for all\"}";
    }

}
