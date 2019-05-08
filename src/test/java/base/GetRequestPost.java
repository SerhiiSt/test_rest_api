package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Sergio on 12/9/18.
 */
public class GetRequestPost {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI="http://www.mapquestapi.com";
        RestAssured.basePath ="/traffic/v2";

    }

    //http://www.mapquestapi.com/traffic/v2/
    // incidents?key=KEY&inFormat=json&outFormat=json

    @Test
    public void statusCodeVerification(){
        given()
                .queryParam("key","t2rgYt7VdtMZnzCA0Q08yqgWs5s3RZMx")
                .body("{\n" +
                        "  \"boundingBox\": {\n" +
                        "    \"ul\": {\n" +
                        "      \"lat\": 39.950960,\n" +
                        "      \"lng\": -105.259451\n" +
                        "    },\n" +
                        "    \"lr\": {\n" +
                        "      \"lat\": 39.528562,\n" +
                        "      \"lng\": -104.710135\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"filters\": [\n" +
                        "    \"construction\",\n" +
                        "    \"incidents\"\n" +
                        "  ]\n" +
                        "} ")
                .queryParam("inFormat","json")
                .queryParam("outFormat","json")

                .when()
                .post("/incidents")
                .then().statusCode(200)
                .and()
                .body("incidents[19].parameterizedDescription.roadName", equalTo("19th Ave"));


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
