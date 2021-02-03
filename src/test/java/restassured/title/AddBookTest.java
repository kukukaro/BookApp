package restassured.title;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import restassured.BookAppApiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AddBookTest extends BookAppApiTest {

//    @BeforeClass
//    public static void initBasePath() {
//        RestAssured.basePath = "title";
//    }


    @Test
    public void addBookWithValidDataToLoggedUserAccount() {
        String title = "Title";
        String author = "Author1";
        int year = 2021;
        int userId = logInSuccessfully();

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

        Integer bookId = respond.getBody().as(Integer.class);
        assertThat(bookId).isPositive();
    }


    private int logInSuccessfully() {
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
}
