package restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.BeforeClass;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BookAppApiTest {

    @BeforeClass
    public static void configureURI() {

        RestAssured.baseURI = "https://ta-ebookrental-be.herokuapp.com";
    }

    protected int logInSuccessfully() {
        String userName = "karo1";
        String password = "karo111";

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", password);

        Response respond = RestAssured
                .given()
                .basePath("user/login")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        Integer userId = respond.getBody().as(Integer.class);
        assertThat(userId).isPositive();
        return userId;
    }

    protected int addBook(String title, String author, int year, int userId) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);

        Response respond = RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .log().all()
                .and()
                .statusCode(200)
                .extract().response();

        return respond.getBody().as(Integer.class);
    }
}
