package restassured.user;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import restassured.BookAppApiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BookAppApiTest {
    @BeforeClass
    public static void initBasePath() {
        RestAssured.basePath = "user/login";
    }

    @Test
    public void logWithValidData() {

        String userName = "test1";
        String password = "test1";


        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", password);

        Response respond = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(respond.getBody().as(Integer.class)).isPositive();
    }

    @Test
    public void logWithInvalidData() {

        String userName = "karo1";
        String password = "karo111";


        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", password);

        Response respond = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(respond.getBody().as(Integer.class)).isEqualTo(-1);
    }

    @Test
    public void logWithEmptyUsername() {

        String userName = "";
        String password = "karo1";


        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", password);

        Response respond = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(respond.getBody().as(Integer.class)).isEqualTo(-1);
    }

    /* Logging with empty password returns user's ID instead code -1.
     */
    @Test
    public void logWithUnregisteredUser() {

        String userName = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);


        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", password);

        Response respond = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(respond.getBody().as(Integer.class)).isEqualTo(-1);
    }

    @Test
    public void logWithEmptyPassword() {

        String userName = "karo1";
        String password = "";


        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", password);

        Response respond = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(respond.getBody().as(Integer.class)).isEqualTo(-1);
    }

    @Test
    public void loginVerifyGetMethod() {

        String userName = "karo1";
        String password = "karo111";


        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", password);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .get()
                .then()
                .statusCode(405)
                .extract().response().prettyPrint();

    }

}
