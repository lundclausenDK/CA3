package test;

import db.DBUtil;
import db.PopulateDB;
import db.TestSettings;
import deploy.DeploymentConfiguration;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
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
import javax.ws.rs.core.MediaType;
import org.apache.catalina.LifecycleException;
import org.apache.http.entity.ContentType;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import security.Secret;
import test.utils.EmbeddedTomcat;

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
        }
        catch (IOException ex) {
            Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void setUpClass() throws ServletException, MalformedURLException, LifecycleException {
        tomcat = new EmbeddedTomcat();
        tomcat.start(SERVER_PORT, APP_CONTEXT);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = SERVER_PORT;
        RestAssured.basePath = APP_CONTEXT;
        RestAssured.defaultParser = Parser.JSON;

        TestSettings.isTesting = true;
        ICollectiveFacade cf = CollectiveFacadeFactory.getTestInstance();
        DBUtil util = new DBUtil(CollectiveFacadeFactory.getTestEmf());

        util.clearDB();
        PopulateDB.populate(cf);
    }

    @AfterClass
    public static void tearDownClass() {
        TestSettings.isTesting = false;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getFirstID() {
        given().when().get("/api/summerhouses").then().body("id[0]", equalTo(1));
    }

    @Test
    public void getAllHomeData() {
        String api = "/api/summerhouses";
        given().when().get(api).then().body("id", hasItems(1, 2))
                .body("title", hasItems("Beautiful home", "Second Most Beautiful Home"))
                .body("address", hasItems("Main Street", "Second Street"))
                .body("geo", hasItems("44.33,22.44", "55.22,55.36"));
    }

    @Test
    public void getSpecificHomeData() {
        String api = "/api/summerhouses/{id}";
        given().pathParams("id", 1).when().get(api).then()
                .body("id", equalTo(1));
    }

    @Test
    public void bookHomeTest() {
        int home_id = 2;
        String api = "/api/summerhouses/rent/{id}";
        String json = "{"
                + "\"start\": \"12/11/2017\","
                + "\"end\": \"14/11/2017\","
                + "\"userName\": \"user\"}";
        given().pathParams("id", home_id).contentType("application/json").body(json).when().post(api).then().body("message", equalTo("reserved: " + home_id));
    }
}
