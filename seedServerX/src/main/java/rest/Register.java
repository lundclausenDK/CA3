package rest;

import com.google.gson.Gson;
import facades.ICollectiveFacade;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import security.CollectiveFacadeFactory;
import entity.User;

@Path("register")
public class Register {
    
    ICollectiveFacade uf = CollectiveFacadeFactory.getInstance();
    Gson gs = new Gson();
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(String content){
      User user = gs.fromJson(content, User.class);
      uf.registerUser(user);
    return "{\"message\" : \"Hello User from Server (Accesible by only authenticated USERS)\"}"; 
  }
 
}