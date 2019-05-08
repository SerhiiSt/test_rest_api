package usefultricks;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.number.OrderingComparison.lessThan;

/**
 * Created by Sergio on 12/24/18.
 */
public class TwitterRootPathExample {
    String consumerKey = "LGx5c7p7Eoi4sDfouCIOMmudO";
    String consumerSecret ="DK4AFwLYShrhnZ2w0fAOrG8Nvdz9BNrwlKIhW9ch1OFUMMAPJA";
    String accessToken ="3730041076-Ioop8ETaPHaRFJBKPIOUFwjxGEjeg8YNJD06HNA";
    String tokenSecret ="KVb3EciXKOA7eKGbFIlT61gXTdD6s09qMoopB9E3dIx6Y";



    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://api.twitter.com";
        RestAssured.basePath ="/1.1/statuses";
        RestAssured.rootPath = "user";

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
                .log().body()
                .statusCode(200)
               // .body("text",hasItem("My first twit from API8"))
               // .rootPath("user")
                .body("name", hasItem("Serhii Shtelmakh"))
                .body("followers_count.size()",lessThan(21))
                .body("profile_link_color", hasItem("1DA1F2"));


    }


}
