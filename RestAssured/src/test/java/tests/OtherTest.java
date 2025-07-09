package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class OtherTest  extends CrudTest{
	
	@Test(dataProvider = "200")
	@Parameters("expStatuscode")
    public void headerAssertions(int expStatuscode) {
        given()
            .spec(requestSpec)
        .when()
            .get("/api/users/2")
        .then()
            .statusCode(expStatuscode)
            .header("Content-Type", equalTo("application/json; charset=utf-8"))
            .headers("Connection", equalTo("keep-alive"));
    }
	
	

    @Test
    public void listSizeAndArrayChecks() {
        given()
            .spec(requestSpec)
        .when()
            .get("/api/users?page=2")
        .then()
            .statusCode(200)
            .body("data", hasSize(6))
            .body("data.id", everyItem(greaterThan(0)))
            .body("data[0].email", containsString("@reqres.in"));
    }
    
    
    
    @Test
    public void nestedJsonAssertion() {
        given()
            .spec(requestSpec)
        .when()
            .get("/api/users/2")
        .then()
            .statusCode(200)
            .body("data.first_name", equalTo("Janet"));
    }
    
    @Test
    public void presenceAndAbsenceOfKeys() {
        given()
            .spec(requestSpec)
        .when()
            .get("/api/users/2")
        .then()
            .body("data", hasKey("id"))
            .body("data", not(hasKey("error")));
    }
    
    
    @Test
    public void stringMatchingChecks() {
        given()
            .spec(requestSpec)
        .when()
            .get("/api/users/2")
        .then()
            .body("support.text", containsString("Help"))
            .body("support.text", equalToIgnoringCase("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
    
    @Test
    public void numericFieldChecks() {
        given()
            .spec(requestSpec)
        .when()
            .get("/api/users/2")
        .then()
            .body("data.id", greaterThan(0))
            .body("data.id", lessThanOrEqualTo(10));
    }
    
    
    @Test
    public void responseTimeCheck() {
        given()
            .spec(requestSpec)
        .when()
            .get("/api/users?page=2")
        .then()
            .time(lessThan(2000L));
    }
    
    
    

    @Test
    public void extractResponseAndVerify() {
        Response response = given()
            .spec(requestSpec)
        .when()
            .get("/api/users?page=2");

        List<String> emails = response.jsonPath().getList("data.email");
        Assert.assertTrue(emails.stream().anyMatch(email -> email.contains("reqres")));
    }
    
    
    
    
    
    
    
    
}




    
    
    

    
  
