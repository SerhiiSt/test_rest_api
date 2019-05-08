package specs;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.number.OrderingComparison.lessThan;

/**
 * Created by Sergio on 12/29/18.
 */
public class ClubbingRequestResponse {
    String consumerKey = "LGx5c7p7Eoi4sDfouCIOMmudO";
    String consumerSecret = "DK4AFwLYShrhnZ2w0fAOrG8Nvdz9BNrwlKIhW9ch1OFUMMAPJA";
    String accessToken = "3730041076-Ioop8ETaPHaRFJBKPIOUFwjxGEjeg8YNJD06HNA";
    String tokenSecret = "KVb3EciXKOA7eKGbFIlT61gXTdD6s09qMoopB9E3dIx6Y";

    RequestSpecBuilder requestSpecBuilder;
    static RequestSpecification requestSpecification;
    ResponseSpecBuilder responseSpecBuilder;
    static ResponseSpecification responseSpecification;


    @BeforeClass
    public void setup() {

        AuthenticationScheme authenticationScheme =
                RestAssured.oauth(consumerKey, consumerSecret, accessToken, tokenSecret);

        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.twitter.com");
        requestSpecBuilder.setBasePath("/1.1/statuses");
        requestSpecBuilder.addQueryParam("user_id", "AAAA");
        requestSpecBuilder.setAuth(authenticationScheme);
        requestSpecification = requestSpecBuilder.build();

        responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectBody("text",hasItem("My first twit from API8"));
        responseSpecBuilder.expectBody("user.name", hasItem("Serhii Shtelmakh"));
        responseSpecBuilder.expectBody("user.followers_count.size()",lessThan(21));
        responseSpecification = responseSpecBuilder.build();




    }

    @Test
    public void readTweet() {

        given()
                .spec(requestSpecification)
                .when()
                .get("/user_timeline.json")
                .then()
                .spec(responseSpecification)
                .body("user.profile_link_color", hasItem("1DA1F2"));


    }



}

