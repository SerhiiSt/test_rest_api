package base;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Sergio on 12/9/18.
 */
public class GetRequestDemoJson {


  @BeforeClass
  public void setup(){
      RestAssured.baseURI="http://www.mapquestapi.com";
      RestAssured.basePath ="/traffic/v2";

  }


  @Test(enabled = false)
  public void statusCodeVerification(){
      given()
              .param("key","t2rgYt7VdtMZnzCA0Q08yqgWs5s3RZMx")
              .param("boundingBox","39.95,-105.25,39.52,-104.71")
              .param("filters","construction,incidents")
              .param("outFormat","json")
      .when()
              .get("/incidents")
      .then().statusCode(200);

  }

    @Test public void getResponseBody(){


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
