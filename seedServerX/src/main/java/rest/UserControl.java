package rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("user_control")
@RolesAllowed("Admin")
public class UserControl 
{
    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUser(String body)
    {
        return "{\"error\": \"Not implemented yet.\"}";
    }
    
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(String body)
    {
        return "{\"error\": \"Not implemented yet.\"}";
    }
    
    @PUT
    @Path("edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editUser(String body)
    {
        return "{\"error\": \"Not implemented yet.\"}";
    }
}
