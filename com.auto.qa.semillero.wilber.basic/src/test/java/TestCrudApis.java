import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.contains;

public class TestCrudApis {
    @BeforeEach
    public void setup(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());

    }
    @Test
    public void PostLoginUnknown(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .post("login")
                .then().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void GetListUnknown(){
        RestAssured.when().get("https://reqres.in/api/unknown").
                then()
                .body("page",equalTo(1));
    }
    @Test
    public void PutUpdateUnknown(){
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"Marlon Nieves\",\n" +
                        "    \"job\": \"QA Automation SENIOR\"\n" +
                        "}")
                .put("https://reqres.in/api/users/7");
    }
    @Test
    public void DeleteUnknown(){
        RestAssured.when().delete("https://reqres.in/api/users/2");
    }
}
