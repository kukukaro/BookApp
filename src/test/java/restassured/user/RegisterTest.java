package restassured.user;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONObject;
import org.junit.Test;
import restassured.BookAppApiTest;

public class RegisterTest extends BookAppApiTest {

    @Test
    public void registerWithValidData() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "karo1");
        requestBody.put("password", "karo1");


        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post("user/register")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

    }

    @Test
    public void registerWithEmptyLogin() {
        RestAssured.baseURI = "https://ta-ebookrental-be.herokuapp.com/user";

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "");
        requestBody.put("password", "empty");


        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post("user/register")
                .then()
                .statusCode(new ResponseCodeNot2xx())
                .extract().response().prettyPrint();
    }

    @Test
    public void registerWithEmptyPassword() {
        RestAssured.baseURI = "https://ta-ebookrental-be.herokuapp.com/user";

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "karo1");
        requestBody.put("password", "");


        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post("user/register")
                .then()
                .statusCode(new ResponseCodeNot2xx())
                .extract().response().prettyPrint();
    }

    @Test
    public void registerVerifyGetMethod() {

        RestAssured.baseURI = "https://ta-ebookrental-be.herokuapp.com/user";

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "karo1");
        requestBody.put("password", "karo1");


        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .get("user/register")
                .then()
                .statusCode(405)
                .extract().response().prettyPrint();

    }

    private static class ResponseCodeNot2xx extends TypeSafeMatcher<Integer> {

        @Override
        public void describeTo(Description description) {
            description.appendText("Http status code not in 2xx range");
        }

        @Override
        protected boolean matchesSafely(Integer item) {
            return item < 200 || item >= 300;
        }
    }

}

