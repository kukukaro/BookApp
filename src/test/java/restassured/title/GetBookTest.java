package restassured.title;

import io.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.Test;
import restassured.BookAppApiTest;

public class GetBookTest extends BookAppApiTest {

    @Test
    public void shouldGetBooks() {

    int userId = logInSuccessfully();

    RestAssured
                .given()
                .basePath("titles/")
                .queryParam("userId", userId)
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .and()
                .statusCode(200)
                .extract().response().prettyPrint();


    }

    /*Get books from not logged user should not be allowed.
    Instead the books is returned.  */
    @Ignore
    @Test
    public void shouldNotGetBooksWithoutLog() {

        RestAssured
                .given()
                .basePath("titles/")
                .queryParam("userId", "11414")
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .and()
                .statusCode(400)
                .extract().response().prettyPrint();

    }


    @Test
    public void shouldNotGetBooksToNoUserIdParameter() {

        RestAssured
                .given()
                .basePath("titles/")
                .queryParam("userId", "")
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .and()
                .statusCode(400)
                .extract().response().prettyPrint();


    }
}
