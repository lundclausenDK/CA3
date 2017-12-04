package test;

import deploy.DeploymentConfiguration;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import security.Secret;
import test.utils.EmbeddedTomcat;

@Ignore
public class InitialSeedRestIntegrationTest {

    private static final int SERVER_PORT = 9999;
    private static final String APP_CONTEXT = "/seed";
    private static EmbeddedTomcat tomcat;

    public InitialSeedRestIntegrationTest() {
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
    private static String securityToken;

    //Utility method to login and set the securityToken
    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        System.out.println(json);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                .when().post("/api/login")
                .then()
                .extract().path("token");
        System.out.println("Token: " + securityToken);
    }

    private static void register(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\", roles: [User]}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                .when().post("/api/register")
                .then()
                .extract().path("token");
    }

    private static void deleteUser(String role) {

    }

    private void logOut() {
        securityToken = null;
    }

    @BeforeClass
    public static void setUpBeforeAll() throws ServletException, MalformedURLException, LifecycleException {
        tomcat = new EmbeddedTomcat();
        tomcat.start(SERVER_PORT, APP_CONTEXT);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = SERVER_PORT;
        RestAssured.basePath = APP_CONTEXT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterClass
    public static void after() throws ServletException, MalformedURLException, LifecycleException, IOException {
        tomcat.stop();
    }

    @Test
    public void testRestNoAuthenticationRequired() {
        given()
                .contentType("application/json")
                .when()
                .get("/api/demouser/demoall").then()
                .statusCode(200)
                .body("message", equalTo("result for all"));
    }

    @Test

    public void tesRestForAdmin() {
        login("admin", "test");
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + securityToken)
                .when()
                .get("/api/demoadmin").then()
                .statusCode(200)
                .body("message", equalTo("Hello Admin from server (call accesible by only authenticated ADMINS)"))
                .body("serverTime", notNullValue());
    }

    @Test
    public void testRestForUser() {
        login("user", "test");
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + securityToken)
                .when()
                .get("/api/demouser").then()
                .statusCode(200)
                .body("message", equalTo("Hello User from Server (Accesible by only authenticated USERS)"));
    }

    @Test
    public void registerNewUserAndDeleteHim() {
        logOut();
        register("testuser11", "test");
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + securityToken)
                .when()
                .get("/api/demouser").then()
                .statusCode(200)
                .body("message", equalTo("Hello User from Server (Accesible by only authenticated USERS)"));
        logOut();
        login("admin", "test");
        String json = String.format("{username: \"%s\"}", "testuser11");
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + securityToken)
                .body(json)
                .when().post("api/user_control/delete")
                .then()
                .statusCode(200);

    }

    @Test

    public void userNotAuthenticated() {
        logOut();
        given()
                .contentType("application/json")
                .when()
                .get("/api/demouser").then()
                .statusCode(401)
                .body("error.message", equalTo("No authorization header provided"));
    }

    @Test

    public void adminNotAuthenticated() {
        logOut();
        given()
                .contentType("application/json")
                .when()
                .get("/api/demoadmin").then()
                .statusCode(401)
                .body("error.message", equalTo("No authorization header provided"));

    }

}
