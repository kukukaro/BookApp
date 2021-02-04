package restassured.title;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import restassured.BookAppApiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AddBookTest extends BookAppApiTest {


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

    /*Sending empty author data in JSON object should return 400 - bad request response.
    Instead the book ID is added.  */
    @Ignore
    @Test
    public void notAddBookWithInvalidDataWithoutAuthorToLoggedUserAccount() {
        String title = "Title";
        String author = "";
        int year = 2021;
        int userId = logInSuccessfully();

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);

        RestAssured
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
                .statusCode(400)
                .extract().response().prettyPrint();

    }

    /*Sending empty title data in JSON object should return 400 - bad request response.
    Instead the book ID is added.  */
    @Ignore
    @Test
    public void notAddBookWithInvalidDataWithoutTitleToLoggedUserAccount() {
        String title = "";
        String author = "Author";
        int year = 2021;
        int userId = logInSuccessfully();

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);

        RestAssured
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
                .statusCode(400)
                .extract().response().prettyPrint();

    }

    /*Sending empty year data in JSON object should return 400 - bad request response.
    Instead the book ID is added.  */
    @Ignore
    @Test
    public void notAddBookWithInvalidDataWithoutYearToLoggedUserAccount() {
        String title = "Title";
        String author = "Author";
        String year = "";
        int userId = logInSuccessfully();

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);

        RestAssured
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
                .statusCode(400)
                .extract().response().prettyPrint();

    }

    @Test
    public void notAddBookWithInvalidYearDataTypeToLoggedUserAccount() {
        String title = "Title";
        String author = "Author";
        String year = "twentyTwenty";
        int userId = logInSuccessfully();

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);

        RestAssured
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
                .statusCode(400)
                .extract().response().prettyPrint();

    }

    /*Sending does not log user's id in JSON object should return 400 - bad request response.
    Instead the book ID is added.  */
    @Ignore
    @Test
    public void notAddBookToNotLoggedUserAccount() {
        String title = "Title";
        String author = "Author";
        int year = 2021;
        int userId = 11485;

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);

        RestAssured
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
                .statusCode(400)
                .extract().response().prettyPrint();

    }

    @Test
    public void notAddBookWithEmptyUserId() {
        String title = "Title";
        String author = "Author";
        int year = 2021;
        String userId = "";

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);

        RestAssured
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
                .statusCode(500)
                .extract().response().prettyPrint();

    }

    @Test
    public void notAddBookToUnregisterUser() {
        String title = "Title";
        String author = "Author";
        int year = 2021;
        int userId = -1;

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);

        RestAssured
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
                .statusCode(500)
                .extract().response().prettyPrint();

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
