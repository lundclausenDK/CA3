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
        /*return "[\n" +
"    {\n" +
"        \"id\": \"1\",\n" +
"        \"title\": \"The Greatest of the all\",\n" +
"        \"address\": \"The nice street\",\n" +
"        \"description\": \"The most beautiful summerhouse you've ever seen\",\n" +
"        \"city\": \"The big city\",\n" +
"        \"picture\": \"https://www.feline.dk/Media/Feline_Holidays_DK/_Profiles/8ce764d7/1e6be63/Sommerhus%20Als_130-F09140.jpg?v=636069406726870339\",\n" +
"        \"price\": \"222\",\n" +
"        \"geo\": \"77.55,53.01\"\n"+
"   },\n" +
"    {\n" +
"        \"id\": \"2\",\n" +
"        \"title\": \"The black summerhouse\",\n" +
"        \"address\": \"The super nice street\",\n" +
"        \"description\": \"The best summerhouse ever\",\n" +
"        \"city\": \"The beautiful city \",\n" +
"        \"picture\": \"https://st.hzcdn.com/simgs/dba10887054a0dc3_4-9552/modern-exterior.jpg\",\n" +
"        \"price\": \"300\",\n" +
"        \"geo\": \"77.53,53.02\"\n"+
"   }\n" +
"]"; */
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
            for (Booking booking : home.getBookings()) {
                JsonObject newBooking = new JsonObject();
                newBooking.addProperty("", "null");
            }
        }
        return homeArray.toString();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpecific(@PathParam("id") int id) {
        return "    {\n"
                + "        \"id\": \"1\",\n"
                + "        \"title\": \"The Greatest of the all\",\n"
                + "        \"address\": \"The nice street\",\n"
                + "        \"description\": \"The most beautiful summerhouse you've ever seen\",\n"
                + "        \"city\": \"The big city\",\n"
                + "        \"picture\": \"https://www.feline.dk/Media/Feline_Holidays_DK/_Profiles/8ce764d7/1e6be63/Sommerhus%20Als_130-F09140.jpg?v=636069406726870339\",\n"
                + "        \"price\": \"222\",\n"
                + "        \"geo\": \"77.55,53.01\"\n"
                + "   }\n";
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
