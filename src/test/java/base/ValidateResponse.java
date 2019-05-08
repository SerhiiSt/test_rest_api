package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
/**
 * Created by Sergio on 12/9/18.
 */
public class ValidateResponse {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI="http://www.mapquestapi.com";
        RestAssured.basePath ="/traffic/v2";

    }


    @Test
    public void statusCodeVerification(){
        given()
                .param("key","t2rgYt7VdtMZnzCA0Q08yqgWs5s3RZMx")
                .param("boundingBox","39.95,-105.25,39.52,-104.71")
                .param("filters","incidents")
                .param("outFormat","json")
                .when()
                .get("/incidents")
                .then().statusCode(200)
                .and()
                //.body("incidents[0].parameterizedDescription.roadName",equalTo("I-25"))
                .contentType(ContentType.JSON);

    }


}
