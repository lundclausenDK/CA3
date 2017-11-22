package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Place;
import entity.Rating;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
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
    ICollectiveFacade cf = CollectiveFacadeFactory.getInstance();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PlacesResource
     */
    public PlacesResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PlacesResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        NumberFormat formatter = new DecimalFormat("#0.0");
        //TODO return proper representation object
        List<Place> placesList = cf.listAllPlaces();

        JsonArray places = new JsonArray();
        for (Place place : placesList) {
            JsonObject placeObject = new JsonObject();
            placeObject.addProperty("id", place.getId());
            placeObject.addProperty("name", place.getName());
            placeObject.addProperty("city", place.getCity());
            placeObject.addProperty("street", place.getStreet());
            placeObject.addProperty("desciption", place.getDescription());
            placeObject.addProperty("url", place.getUrl());
            placeObject.addProperty("zip", place.getZip());
            placeObject.addProperty("geo", place.getGeo());

            JsonArray raters = new JsonArray();
            List<Rating> ratings = place.getRatings();
            int ratingValue = 0;
            int actualRaters = 0;
            if (ratings.size() > 0) {
                for (Rating rating : ratings) {
                    JsonObject user = new JsonObject();
                    user.addProperty("user", rating.getUser().getUserName());
                    raters.add(user);
                    ratingValue += rating.getRatingValue();
                    actualRaters += 1;
                }
                placeObject.add("raters", raters);
                placeObject.addProperty("rating", formatter.format(ratingValue / actualRaters));
            }else{
                placeObject.add("raters", raters);
                placeObject.addProperty("rating", 0);
                
            }

            places.add(placeObject);
        }
        return places.toString();
        //return gs.toJson(cf.listAllPlaces());
        //return "{content: 'hello'}";
    }
    /**
     * PUT method for updating or creating an instance of PlacesResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
