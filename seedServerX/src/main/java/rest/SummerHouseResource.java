package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("summerhouses")
public class SummerHouseResource {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "[\n" +
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
"]"; 
  }
  
  @Path("{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSpecific(@PathParam("id") int id){
      return "    {\n" +
"        \"id\": \"1\",\n" +
"        \"title\": \"The Greatest of the all\",\n" +
"        \"address\": \"The nice street\",\n" +
"        \"description\": \"The most beautiful summerhouse you've ever seen\",\n" +
"        \"city\": \"The big city\",\n" +
"        \"picture\": \"https://www.feline.dk/Media/Feline_Holidays_DK/_Profiles/8ce764d7/1e6be63/Sommerhus%20Als_130-F09140.jpg?v=636069406726870339\",\n" +
"        \"price\": \"222\",\n" +
"        \"geo\": \"77.55,53.01\"\n"+
"   }\n";
  }
 
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String postHouse(String content){
      return "{\"message\": \"done\"}";
  }
  
  @Path("rent/{id}")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String reservedHome(String content, @PathParam("id") int id) {
      return "{\"message\": \"reserved: "+ id +"\"}";
  }
  
  
}