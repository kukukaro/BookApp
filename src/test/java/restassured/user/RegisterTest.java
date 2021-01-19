package restassured.user;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import restassured.BookAppApiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterTest extends BookAppApiTest {

    @BeforeClass
    public static void initBasePath() {
        RestAssured.basePath = "user/register";
    }

    @Test
    public void registerWithValidData() {

        String userName = RandomStringUtils.randomAlphabetic(10);

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", "karo1");


        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        response.prettyPrint();

        boolean isNew = response.jsonPath().getBoolean("new");

        assertThat(isNew).isTrue();

    }

    /*This should be no possibility to create user with empty login.
    Test currently reports valid http status code because user with empty name exists in application*/
    @Ignore
    @Test
    public void registerWithEmptyLogin() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "");
        requestBody.put("password", "empty");


        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(new ResponseCodeNot2xx())
                .extract().response().prettyPrint();
    }

    /*This should be no possibility to create user with empty password. */
    @Ignore
    @Test
    public void registerWithEmptyPassword() {

        String userName = RandomStringUtils.randomAlphabetic(10);

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", userName);
        requestBody.put("password", "");

        ValidatableResponse validatableResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then();

        validatableResponse.extract().response().prettyPrint();
        validatableResponse.statusCode(new ResponseCodeNot2xx());
    }

    @Test
    public void registerVerifyGetMethod() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "karo1");
        requestBody.put("password", "karo1");


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

    @Test
    public void registerVerifyDeleteMethod() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "karo1");
        requestBody.put("password", "karo1");


        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .delete()
                .then()
                .statusCode(405)
                .extract().response().prettyPrint();

    }

    /*Sending empty JSON object should return 400 - bad request response.
    Instead mysterious user object with ID is returned.  */
    @Ignore
    @Test
    public void checkResponseFromEmptyJsonBody() {

        JSONObject emptyObject = new JSONObject();

        ValidatableResponse validatableResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(emptyObject.toString())
                .log().all()
                .when()
                .post()
                .then();

        validatableResponse.extract().response().prettyPrint();
        validatableResponse.statusCode(400);
    }

    @Test
    public void checkResponseFromEmptyBody() {

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(400)
                .extract().response().prettyPrint();
    }

    @Test
    public void checkResponseFromTextContentType() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "karo1");
        requestBody.put("password", "karo1");


        RestAssured
                .given()
                .contentType(ContentType.TEXT)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(415)
                .extract().response().prettyPrint();

    }

    @Test
    public void shouldNotRegisterOnWrongRegisterEndpoint() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", "karo1");
        requestBody.put("password", "karo1");


        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post("/dummy")
                .then()
                .statusCode(404)
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

