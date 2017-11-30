package test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.Ignore;

/**
 *
 * @author craci
 */
@Ignore
public class SummerhouseRestTest {
    
    public SummerhouseRestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/seedMaven";
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    //@Ignore
    @Test
    public void getFirstID() {
        given().when().get("/api/summerhouses").then().body("id[0]", equalTo("1"));
    }
    
    //@Ignore
    @Test
    public void getAllHomeData() {
        String api = "/api/summerhouses";
        given().when().get(api).then().body("id", hasItems("1", "2"))
                .body("title", hasItems("The Greatest of the all","The black summerhouse"))
                .body("address", hasItems("The nice street","The super nice street"))
                .body("geo", hasItems("77.55,53.01", "77.53,53.02"));
    }
    
    //@Ignore
    @Test
    public void getSpecificHomeData(){
        String api ="/api/summerhouses/{id}";
        given().pathParams("id", 1).when().get(api).then()
                .body("id", equalTo("1"));
    }
    
    @Test
    public void putMethodTesting(){
        String api = "/api/summerhouses";
        String json = "{"
                + "'title': 'Super summerhouse',"
                + "'address': 'gadevejen 32',"
                + "'desctiption': 'Gammel skøn klassiker',"
                + "'city': 'Bystaden',"
                + "'picture': 'hus.jpg',"
                + "'price': '3000',"
                + "'geo': '44.55,55.44'}";
        given().contentType("application/json").body(json).when().post(api).then().body("message", equalTo("done"));
    }
    
    @Test
    public void rentHomeTest(){
        int home_id = 2;
        String api = "/api/summerhouses/rent/{id}";
        String json = "{"
                + "'start': '10500500',"
                + "'end': '20500500',"
                + "'user_id': '1'}";
        given().pathParams("id", home_id).contentType("application/json").body(json).when().post(api).then().body("message", equalTo("reserved: " + home_id));
    }
}
