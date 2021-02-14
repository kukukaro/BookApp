package restassured.title;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.Test;
import restassured.BookAppApiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteBookTest extends BookAppApiTest {

    @Test
    public void shouldDeleteBook() {

        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, userId);

        Response response = RestAssured
                .given()
                .basePath("titles/")
                .queryParam("userId", userId)
                .queryParam("id", bookId)
                .when()
                .delete()
                .then()
                .statusCode(200)
                .extract().response();

        boolean isNew = response.getBody().as(Boolean.class);

        assertThat(isNew).isTrue();
    }

    /*Delete book from not logged user should not be allowed.
    Instead the books was deleted.  */
    @Ignore
    @Test
    public void shouldNotDeleteBookWithoutLog() {
        int userId = 11414;
        int bookId = addBook("TitleTest", "AuthorTest", 2020, userId);

        RestAssured
                .given()
                .basePath("titles/")
                .queryParam("userId", userId)
                .queryParam("id", bookId)
                .when()
                .delete()
                .then()
                .statusCode(400)
                .extract().response().prettyPrint();
    }

    @Test
    public void shouldNotDeleteBookWithoutUserId() {

        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, userId);

        RestAssured
                .given()
                .basePath("titles/")
                .queryParam("userId", "")
                .queryParam("id", bookId)
                .when()
                .delete()
                .then()
                .statusCode(400)
                .extract().response().prettyPrint();
    }

    @Test
    public void shouldNotDeleteBookWithoutBookId() {

        int userId = logInSuccessfully();
        int bookId = addBook("TitleTest", "AuthorTest", 2020, userId);

        RestAssured
                .given()
                .basePath("titles/")
                .queryParam("userId", userId)
                .queryParam("id", "")
                .when()
                .delete()
                .then()
                .statusCode(400)
                .extract().response().prettyPrint();

    }

}
