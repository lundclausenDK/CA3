package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import entity.Booking;
import entity.Home;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import uploadHelper.ImageUpload;

@Path("summerhouses")
public class SummerHouseResource {

    private ICollectiveFacade uf = CollectiveFacadeFactory.getInstance();
    private Gson gs = new Gson();
    private ImageUpload imageUpload = new ImageUpload();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething()
    {
        List<Home> homeList = uf.listAllHomes();
        JsonArray homeArray = new JsonArray();
        for (Home home : homeList)
        {
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
            for (Booking booking : home.getBookings())
            {
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
    public String getSpecific(@PathParam("id") int id)
    {
        try
        {
            Home home = uf.findHomeById(id);
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
            for (Booking booking : home.getBookings())
            {
                JsonObject newBooking = new JsonObject();
                newBooking.addProperty("startDate", booking.getStartTime());
                newBooking.addProperty("endDate", booking.getEndTime());
                newBooking.addProperty("user", booking.getRenter().getUserName());
                bookings.add(newBooking);
            }
            homeDetails.add("bookings", bookings);
            return homeDetails.toString();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @POST
    @Path("add_home")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public String postHouse(@DefaultValue("") @FormDataParam("name") String name,
            @DefaultValue("") @FormDataParam("desc") String info,
            @DefaultValue("") @FormDataParam("geo") String geo,
            @DefaultValue("") @FormDataParam("street") String street,
            @FormDataParam("zip") int zip,
            @DefaultValue("") @FormDataParam("city") String city,
            @FormDataParam("price") double price,
            @FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileDisposition)
    {
        try
        {
            String picture = fileDisposition.getFileName();

            Home home = new Home(name, info, street, zip, city, geo, price, picture);
            imageUpload.saveFile(file, picture);
            uf.addHome(home);

            return "{\"message\": \"done\"}";
        }
        catch (JsonSyntaxException | IOException e )
        {
            e.printStackTrace();
            return "{\"error\": \"Couldn't add home\"}";
        }

    }

    @Path("rent/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String reservedHome(String content, @PathParam("id") int id)
    {
        try
        {
            JsonObject json = new JsonParser().parse(content).getAsJsonObject();
            String userName = json.get("userName").getAsString();
            int start = json.get("start").getAsInt();
            int end = json.get("end").getAsInt();
            uf.bookHome(id, userName, start, end);
            return "{\"message\": \"reserved: " + id + "\"}";
        }
        catch (Exception e)
        {
            return "{\"message\": \"ERROR\"}";
        }
    }

}
