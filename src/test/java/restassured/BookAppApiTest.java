package restassured;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public abstract class BookAppApiTest {

    @BeforeClass
    public static void configureURI() {

        RestAssured.baseURI = "https://ta-ebookrental-be.herokuapp.com";
    }

}
