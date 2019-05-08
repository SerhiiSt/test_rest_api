package twitter_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Sergio on 12/21/18.
 */
public class TwitterEndToEndWorkFlow {
    String consumerKey = "fcf8cJHfNOfpb6oG7cMKcqDR5";
    String consumerSecret ="qrSdHohDW4YaoHnQWddu48SdXttC0QFpmt9FguMbORYpniIFqL";
    String accessToken ="3730041076-Ioop8ETaPHaRFJBKPIOUFwjxGEjeg8YNJD06HNA";
    String tokenSecret ="KVb3EciXKOA7eKGbFIlT61gXTdD6s09qMoopB9E3dIx6Y";

    String tweetId = "";


    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://api.twitter.com";
        RestAssured.basePath ="/1.1/statuses";

    }


    @Test
    public void postTweet(){
        Response response =   given()
                .auth()
                .oauth(consumerKey,consumerSecret,accessToken,tokenSecret)
                .queryParam("status","BBBB")
                .when()
                .post("/update.json")
                .then().statusCode(200)
                .extract()
                .response();

        tweetId= response.path("id_str");
        System.out.println("The id is "+tweetId);




    }

    @Test(dependsOnMethods ={"postTweet"} )
    public void readTweet(){
        Response response =   given()
                .auth()
                .oauth(consumerKey,consumerSecret,accessToken,tokenSecret)
                .queryParam("id",tweetId)
                .when()
                .get("/show.json")
                .then()
                .extract()
                .response();

        String text = response.path("text");
        System.out.println("The text is: "+text);



    }

    @Test(dependsOnMethods ={"readTweet"} )
    public void deleteTweet(){
        given()
                .auth()
                .oauth(consumerKey,consumerSecret,accessToken,tokenSecret)
                .queryParam("id",tweetId)
                .when()
                .post("/destroy.json")
                .then()
                .statusCode(200);

    }

    @Test(dependsOnMethods ={"readTweet"} )
    public void deleteTweetWithPathParam(){
        given()
                .auth()
                .oauth(consumerKey,consumerSecret,accessToken,tokenSecret)
                .pathParam("id",tweetId)
                .when()
                .post("/destroy/{id}.json")
                .then()
                .statusCode(200);

    }

}
