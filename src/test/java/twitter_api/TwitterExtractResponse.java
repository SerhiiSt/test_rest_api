package twitter_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Sergio on 12/9/18.
 */
public class TwitterExtractResponse {

    String consumerKey = "3VSFRPy4RD0VFRZUMOBcPl1IO";
    String consumerSecret ="dE1FzMJPSMek52f9RkjD4BnsXj0LX9gQxKPN7nqs92m8mNHqYx";
    String accessToken ="3730041076-Ioop8ETaPHaRFJBKPIOUFwjxGEjeg8YNJD06HNA";
    String tokenSecret ="KVb3EciXKOA7eKGbFIlT61gXTdD6s09qMoopB9E3dIx6Y";


    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://api.twitter.com";
        RestAssured.basePath ="/1.1/statuses";

    }


    @Test
    public void extractResponse(){
     Response response =   given()
                .auth()
                .oauth(consumerKey,consumerSecret,accessToken,tokenSecret)
                .queryParam("status","Test3")


                .when()
                .post("/update.json")
                .then().statusCode(200)
                .extract().response(); // 1 way to extract json

        String id = response.path("id_str");
        System.out.println("The id is "+id);

        String responseString = response.prettyPrint();
        System.out.println(responseString);

        JsonPath jsonPath = new JsonPath(responseString); // 2 way to extract json
        String name = jsonPath.get("user.name");
        String screenName = jsonPath.get("user.screen_name");
        System.out.println("The name of user is: " + name );
        System.out.println("The  screen name of user is: " + screenName );



    }

    @Test (enabled = false)
    public void getResponseBody(){


        Response response =
                given()
                        .param("key","t2rgYt7VdtMZnzCA0Q08yqgWs5s3RZMx")
                        .param("boundingBox","39.95,-105.25,39.52,-104.71")
                        .param("filters","incidents")
                        .param("outFormat","json")
                        .when()
                        .get("/incidents");

        System.out.println(response.body().prettyPrint());

    }
}
