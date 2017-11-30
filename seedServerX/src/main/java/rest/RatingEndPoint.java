package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("rate")
@RolesAllowed("User")
public class RatingEndPoint {
    
    ICollectiveFacade cf = CollectiveFacadeFactory.getInstance();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postRate(String content) {
        JsonObject json = new JsonParser().parse(content).getAsJsonObject();
        int locationID = json.get("locationID").getAsInt();
        int rating = json.get("rating").getAsInt();
        String userName = json.get("userName").getAsString();
        cf.addRating(locationID, rating, userName);
    }

}
