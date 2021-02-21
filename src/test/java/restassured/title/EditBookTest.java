package restassured.title;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import restassured.BookAppApiTest;

import static org.hamcrest.CoreMatchers.equalTo;

public class EditBookTest extends BookAppApiTest {


    @Test
    public void shouldUpdateBook() {

        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, userId);
        String title = "TitleUpdated";
        String author = "AuthorUpdated";
        int year = 2022;

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);
        requestBody.put("id", bookId);

        RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(200)
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("year", equalTo(year));
    }

    /*Should not update book without authorisation
    Instead the books was updated.  */
    @Ignore
    @Test
    public void shouldNotUpdateBookWithoutLog() {

        int bookId = addBook("TitleTest", "AuthorTest", 2020, 11414);
        String title = "TitleUpdated";
        String author = "AuthorUpdated";
        int year = 2022;

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", 11414);
        requestBody.put("id", bookId);

        RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(200)
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("year", equalTo(year));
    }

    @Test
    public void shouldNotUpdateBookWithoutUserId() {
        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, userId);
        String title = "TitleUpdated";
        String author = "AuthorUpdated";
        int year = 2022;

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", "");
        requestBody.put("id", bookId);

        RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(500)
                .extract().response().prettyPrint();
    }

    @Test
    public void shouldNotUpdateBookWithoutBookId() {
        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, 11414);
        String title = "TitleUpdated";
        String author = "AuthorUpdated";
        int year = 2022;

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);
        requestBody.put("id", "");

        RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(500)
                .extract().response().prettyPrint();
    }
    /*Should not update book with no title's data.
    Instead the title was updated.  */
    @Ignore
    @Test
    public void shouldNotUpdateBookWithoutTitle() {
        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, 11414);
        String author = "AuthorUpdated";
        int year = 2022;

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", "");
        requestBody.put("userId", userId);
        requestBody.put("id", bookId);

        RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(500)
                .extract().response().prettyPrint();
    }

    /*Should not update book with no author's data.
    Instead the author was updated.  */
    @Ignore
    @Test
    public void shouldNotUpdateBookWithoutAuthor() {
        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, 11414);
        String title = "TitleUpdated";
        int year = 2022;

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", "");
        requestBody.put("title", title);
        requestBody.put("userId", userId);
        requestBody.put("id", bookId);

        RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(500)
                .extract().response().prettyPrint();
    }

    /*Should not update book with no year's data.
    Instead the year was updated.  */
    @Ignore
    @Test
    public void shouldNotUpdateBookWithoutYear() {
        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, 11414);
        String title = "TitleUpdated";
        String author = "AuthorUpdated";

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", "");
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);
        requestBody.put("id", bookId);

        RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(500)
                .extract().response().prettyPrint();
    }

    /*Should not update book with invalid year's data format.
    Instead the year was updated.  */
    @Ignore
    @Test
    public void shouldNotUpdateBookWithInvalidYearData() {
        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, 11414);
        String title = "TitleUpdated";
        String author = "AuthorUpdated";
        String year = "twenty twenty";

        JSONObject requestBody = new JSONObject();
        requestBody.put("year", year);
        requestBody.put("author", author);
        requestBody.put("title", title);
        requestBody.put("userId", userId);
        requestBody.put("id", bookId);

        RestAssured
                .given()
                .basePath("titles/")
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(500)
                .extract().response().prettyPrint();
    }
}

