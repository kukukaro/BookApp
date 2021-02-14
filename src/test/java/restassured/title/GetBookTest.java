package restassured.title;

import io.restassured.RestAssured;
import org.junit.Test;
import restassured.BookAppApiTest;

public class GetBookTest extends BookAppApiTest {

    @Test
    public void shouldGetBooks() {

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
                .statusCode(200)
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
