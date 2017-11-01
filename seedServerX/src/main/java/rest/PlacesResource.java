package rest;

import com.google.gson.Gson;
import facades.CollectiveFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;


@Path("places")
public class PlacesResource {
    
    Gson gs = new Gson();
    //PlaceFacade pf = new PlaceFacade();
    CollectiveFacade cf;
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PlacesResource
     */
    public PlacesResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PlacesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        
        return gs.toJson("{content: hello}");
    }

    /**
     * PUT method for updating or creating an instance of PlacesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
