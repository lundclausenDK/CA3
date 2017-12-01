package test;

import deploy.DeploymentConfiguration;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.parsing.Parser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.Ignore;
import security.Secret;
import test.utils.EmbeddedTomcat;

/**
 *
 * @author craci
 */
//@Ignore
public class SummerhouseRestTest {
    
    private static final int SERVER_PORT = 9999;
    private static final String APP_CONTEXT = "/seed";
    private static EmbeddedTomcat tomcat;
    
    public SummerhouseRestTest() {
        String content = "tokenSecret=c6hFJOYY75765444EEEEvgTdeMnbV30h";

        Properties prop = new Properties();
        try (InputStream input = new ByteArrayInputStream(content.getBytes())) {
            prop.load(input);
            Secret.SHARED_SECRET = prop.getProperty("tokenSecret").getBytes();
        } catch (IOException ex) {
            Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static String securityToken;
    
    private static void login(String username, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", username, password);
        System.out.println(json);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                .when().post("/api/login")
                .then()
                .extract().path("token");
        System.out.println("Token: " + securityToken);
    }
    
    @BeforeClass
    public static void setUpClass() throws ServletException, MalformedURLException, LifecycleException{
        tomcat = new EmbeddedTomcat();
        tomcat.start(SERVER_PORT, APP_CONTEXT);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = SERVER_PORT;
        RestAssured.basePath = APP_CONTEXT;
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
    
    @Test
    public void getFirstID() {
        given().when().get("/api/summerhouses").then().body("id[0]", equalTo(1));
    }
    
    //@Ignore
    @Test
    public void getAllHomeData() {
        String api = "/api/summerhouses";
        given().when().get(api).then().body("id", hasItems(1, 2))
                .body("title", hasItems("Beautiful home","Second Most Beautiful Home"))
                .body("address", hasItems("Main Street","Second Street"))
                .body("geo", hasItems("44.33,22.44", "55.22,55.36"));
    }
    
    @Test
    public void getSpecificHomeData(){
        String api ="/api/summerhouses/{id}";
        given().pathParams("id", 1).when().get(api).then()
                .body("id", equalTo(1));
    }
    
    @Ignore
    @Test
    public void putMethodTesting(){
        login("admin", "test");
        String api = "/api/summerhouses/add_home";
        String json = "{"
                + "\"name\": \"Super summerhouse\","
                + "\"street\": \"gadevejen 32\","
                + "\"desc\": \"Gammel skøn klassiker\","
                + "\"city\": \"Bystaden\","
                + "\"picture\": \"hus.jpg\","
                + "\"price\": \"3000\","
                + "\"geo\": \"44.55,55.44\"}";
        given().contentType("application/json").header("Authorization", "Bearer " + securityToken).body(json).when().post(api).then().body("message", equalTo("done"));
    }
    
    @Test
    public void rentHomeTest(){
        int home_id = 2;
        String api = "/api/summerhouses/rent/{id}";
        String json = "{"
                + "\"start\": \"12/11/2017\","
                + "\"end\": \"14/11/2017\","
                + "\"userName\": \"1\"}";
        given().pathParams("id", home_id).contentType("application/json").body(json).when().post(api).then().body("message", equalTo("reserved: " + home_id));
    }
}
