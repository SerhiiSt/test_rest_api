package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Sergio on 12/23/18.
 */
public class GetRequestDemoXml {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI="http://www.mapquestapi.com";
        RestAssured.basePath ="/traffic/v2";

    }

    @Test
    public void getResponseBody(){


        Response response =
                given()  .contentType(ContentType.XML)
                        .queryParam("key","t2rgYt7VdtMZnzCA0Q08yqgWs5s3RZMx")
                        .queryParam("boundingBox","39.95,-105.25,39.52,-104.71")
                        .queryParam("filters","incidents")
                        .queryParam("outFormat","xml")
                        .when()
                        .get("/incidents")
                        .then()
                        .statusCode(200)
                        .extract().response();

        String stringResponse = response.prettyPrint();
        System.out.println(stringResponse);
//        String value = response.path("incidents.incident.h3");
//
//        System.out.println("The value is : "+value);

        XmlPath xmlPath = new XmlPath(stringResponse);
        String stringResponsePath = xmlPath.get("incidents.incident.h3");
        System.out.println(stringResponsePath);


    }
}
