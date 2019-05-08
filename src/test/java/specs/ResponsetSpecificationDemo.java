package specs;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.number.OrderingComparison.lessThan;

/**
 * Created by Sergio on 12/26/18.
 */
public class ResponsetSpecificationDemo {

        String consumerKey = "LGx5c7p7Eoi4sDfouCIOMmudO";
        String consumerSecret = "DK4AFwLYShrhnZ2w0fAOrG8Nvdz9BNrwlKIhW9ch1OFUMMAPJA";
        String accessToken = "3730041076-Ioop8ETaPHaRFJBKPIOUFwjxGEjeg8YNJD06HNA";
        String tokenSecret = "KVb3EciXKOA7eKGbFIlT61gXTdD6s09qMoopB9E3dIx6Y";

        ResponseSpecBuilder responseSpecBuilder;
        static ResponseSpecification responseSpecification;

        @BeforeClass
        public void setup() {
            RestAssured.baseURI = "https://api.twitter.com";
            RestAssured.basePath = "/1.1/statuses";
            responseSpecBuilder = new ResponseSpecBuilder();
            responseSpecBuilder.expectStatusCode(200);
            responseSpecBuilder.expectResponseTime(lessThan(3L), TimeUnit.SECONDS);
            responseSpecBuilder.expectBody("text",hasItem("My first twit from API8"));
            responseSpecBuilder.expectBody("user.name", hasItem("Serhii Shtelmakh"));
            responseSpecBuilder.expectBody("user.followers_count.size()",lessThan(21));


            responseSpecification = responseSpecBuilder.build();




        }

        @Test
        public void readTweet() {

            given()
                    .auth()
                    .oauth(consumerKey, consumerSecret, accessToken, tokenSecret)
                    .queryParam("user_id", "AAAA")
                    .when()
                    .get("/user_timeline.json")
                    .then()

//                    .statusCode(200)
//                    .body("text",hasItem("My first twit from API8"))
//                    .body("user.name", hasItem("Serhii Shtelmakh"))
//                    .body("user.followers_count.size()",lessThan(21))
//                    .body("user.profile_link_color", hasItem("1DA1F2"));
                    .spec(responseSpecification)
                    .body("user.profile_link_color", hasItem("1DA1F2"));


        }

    }

