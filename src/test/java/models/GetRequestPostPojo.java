package models;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Sergio on 12/10/18.
 */
public class GetRequestPostPojo {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI="http://www.mapquestapi.com";
        RestAssured.basePath ="/traffic/v2";

    }


    @Test
    public void statusCodeVerificationPojo(){




        HashMap<String,Double> ul = new HashMap<String, Double>();
        ul.put("lat",39.950960);
        ul.put("lng", -105.259451);

        HashMap<String,Double>  lr= new HashMap<String, Double>();
        lr.put("lat", 39.528562);
        lr.put("lng", -104.710135);


        HashMap<String,HashMap<String,Double>> boundingBox = new HashMap<>();
        boundingBox.put("ul",ul);
        boundingBox.put("lr",lr);


          Model model = new Model();
          List<String> filters = new ArrayList<>();
          filters.add("construction");
          filters.add("incidents");
          model.setFilters(filters);
          model.setBoundingBox(boundingBox);






        given()

                .queryParam("key","t2rgYt7VdtMZnzCA0Q08yqgWs5s3RZMx")
                .body(model)
                .queryParam("inFormat","json")
                .queryParam("outFormat","json")

                .when()
                .post("/incidents")
                .then().statusCode(200)
                .and()
                .body("incidents[19].parameterizedDescription.roadName", equalTo("Larimer St"));


    }

//   @Test (enabled = false)
//    public void getResponseBody(){
//
//
//        Response response =
//                given()
//                        .param("key","t2rgYt7VdtMZnzCA0Q08yqgWs5s3RZMx")
//                        .param("boundingBox","39.95,-105.25,39.52,-104.71")
//                        .param("filters","incidents")
//                        .param("outFormat","json")
//                        .when()
//                        .get("/incidents");
//
//        System.out.println(response.body().prettyPrint());
//
//    }
}
