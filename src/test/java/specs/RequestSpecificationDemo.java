package specs;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.number.OrderingComparison.lessThan;

/**
 * Created by Sergio on 12/26/18.
 */
public class RequestSpecificationDemo {

        String consumerKey = "LGx5c7p7Eoi4sDfouCIOMmudO";
        String consumerSecret = "DK4AFwLYShrhnZ2w0fAOrG8Nvdz9BNrwlKIhW9ch1OFUMMAPJA";
        String accessToken = "3730041076-Ioop8ETaPHaRFJBKPIOUFwjxGEjeg8YNJD06HNA";
        String tokenSecret = "KVb3EciXKOA7eKGbFIlT61gXTdD6s09qMoopB9E3dIx6Y";

        RequestSpecBuilder requestSpecBuilder;
        static RequestSpecification requestSpecification;

        @BeforeClass
        public void setup() {
            RestAssured.baseURI = "https://api.twitter.com";
            RestAssured.basePath = "/1.1/statuses";

            AuthenticationScheme authenticationScheme =
                    RestAssured.oauth(consumerKey, consumerSecret, accessToken, tokenSecret);

            requestSpecBuilder = new RequestSpecBuilder();
            requestSpecBuilder.setBaseUri("https://api.twitter.com");
            requestSpecBuilder.setBasePath("/1.1/statuses");
            requestSpecBuilder.addQueryParam("user_id", "AAAA");
            requestSpecBuilder.setAuth(authenticationScheme);

            requestSpecification = requestSpecBuilder.build();


        }

        @Test
        public void readTweet() {

            given()
                    .spec(requestSpecification)
            .when()
                    .get("/user_timeline.json")
            .then()
                    .log().body()
                    .statusCode(200);


        }

    }

