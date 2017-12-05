package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Place;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.annotation.security.RolesAllowed;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import uploadHelper.ImageUpload;

@Path("upload")
public class UploadResource {

    private ICollectiveFacade uf = CollectiveFacadeFactory.getInstance();
    private String path = "/var/www/img/";
    private ImageUpload imageUpload = new ImageUpload();

    @Context
    private UriInfo context;

    public UploadResource() {
        File folder = new File(path);
        folder.mkdir();
    }

    @Path("/place")
    @POST
    @RolesAllowed("Admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadHouse(String content) {
        JsonObject json = new JsonParser().parse(content).getAsJsonObject();
        String name = json.get("name").getAsString();
        String info = json.get("description").getAsString();
        String street = json.get("street").getAsString();
        String city = json.get("city").getAsString();
        int zip = json.get("zip").getAsInt();

        Place place = new Place(name, city, street, info, zip);
        uf.createPlace(place);

        String status = "{\"status\":\"uploaded\"}";
        return Response.ok(status).build();
    }

    @Path("/placeUpload")
    @POST
    @RolesAllowed("User")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPlace(@DefaultValue("") @FormDataParam("name") String name,
            @DefaultValue("") @FormDataParam("desc") String info,
            @DefaultValue("") @FormDataParam("geo") String geo,
            @DefaultValue("") @FormDataParam("street") String street,
            @DefaultValue("") @FormDataParam("zip") String zip,
            @DefaultValue("") @FormDataParam("city") String city,
            @DefaultValue("") @FormDataParam("userName") String userName,
            @FormDataParam("rating") int rating,
            @FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileDisposition) {

        String fileName = fileDisposition.getFileName();
        System.out.println(fileName);
        int placeZip = Integer.parseInt(zip);

        if (rating < 1)
            rating = 1;

        try {

            Place place = new Place(name, city, street, info, fileName, placeZip, geo);
            //saveFile(file, path + fileName);
            imageUpload.saveFile(file, fileName);

            uf.createPlace(place);

            Place found = uf.findPlaceByName(name);

            if (found != null) {
                uf.addRating(found.getId(), rating, userName);
            }

        }
        catch (IOException ex) {
            Logger.getLogger(UploadResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        String status = "{\"status\":\"uploaded\"}";
        return Response.ok(status).build();
    }
}
