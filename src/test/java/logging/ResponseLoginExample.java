package logging;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Sergio on 12/9/18.
 */
public class ResponseLoginExample {

    String consumerKey = "zHf4fbp8PDrkyKacgYJg6Dvdd";
    String consumerSecret ="990MJOBaYyWs2lcXQqHOeBLl4HY7GyZsU2ZCxrcc9hoLgQjNHT";
    String accessToken ="3730041076-Ioop8ETaPHaRFJBKPIOUFwjxGEjeg8YNJD06HNA";
    String tokenSecret ="KVb3EciXKOA7eKGbFIlT61gXTdD6s09qMoopB9E3dIx6Y";


    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://api.twitter.com";
        RestAssured.basePath ="/1.1/statuses";

    }


    @Test
    public void testMethod(){
        given()
                .auth()
                .oauth(consumerKey,consumerSecret,accessToken,tokenSecret)
                .queryParam("status","My first twit from API8")


                .when()
                .post("/update.json")
                .then()
                .log()
                //.body()
                //.headers()
                //.all()
                //.ifValidationFails()
                .ifError()
                .statusCode(201);



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
