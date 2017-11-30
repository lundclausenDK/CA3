package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Booking;
import entity.Home;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("summerhouses")
public class SummerHouseResource {

    ICollectiveFacade uf = CollectiveFacadeFactory.getInstance();
    Gson gs = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        List<Home> homeList = uf.listAllHomes();
        JsonArray homeArray = new JsonArray();
        for (Home home : homeList) {
            JsonObject homeDetails = new JsonObject();
            homeDetails.addProperty("id", home.getId());
            homeDetails.addProperty("title", home.getName());
            homeDetails.addProperty("address", home.getAddress());
            homeDetails.addProperty("description", home.getDescription());
            homeDetails.addProperty("city", home.getCity());
            homeDetails.addProperty("picture", "hej");
            homeDetails.addProperty("price", home.getPrice());
            homeDetails.addProperty("geo", home.getGeo());

            JsonArray bookings = new JsonArray();
            for (Booking booking : home.getBookings()) {
                JsonObject newBooking = new JsonObject();
                newBooking.addProperty("startDate", booking.getStartTime());
                newBooking.addProperty("endDate", booking.getEndTime());
                newBooking.addProperty("user", booking.getRenter().getUserName());
                bookings.add(newBooking);
            }
            homeDetails.add("bookings", bookings);

            homeArray.add(homeDetails);
        }
        return homeArray.toString();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpecific(@PathParam("id") int id) {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postHouse(String content) {
        return "{\"message\": \"done\"}";
    }

    @Path("rent/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String reservedHome(String content, @PathParam("id") int id) {
        try {
            return "{\"message\": \"reserved: " + id + "\"}";
        } catch (Exception e) {
            return "{\"message\": \"ERROR\"}";
        }
    }

}
