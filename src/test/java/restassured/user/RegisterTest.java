package restassured.user;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONObject;
import org.junit.Test;
import restassured.BookAppApiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterTest extends BookAppApiTest {

    @Test
    public void registerWithValidData() {

        String userName = RandomStringUtils.randomAlphabetic(10);

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", "karo1");


        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .basePath("user")
                .body(requestBody.toString())
                .log().all()
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .extract().response();

        response.prettyPrint();

        boolean isNew = response.jsonPath().getBoolean("new");

        assertThat(isNew).isTrue();

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

