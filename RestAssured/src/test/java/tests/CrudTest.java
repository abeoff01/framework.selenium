package tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basetest.BaseTest;
import io.restassured.response.Response;
import utils.PayloadManager;

public class CrudTest extends BaseTest {

	
	
	
	SoftAssert ass = new SoftAssert();
	
	Response response;
	
	
	

	public Object[][] excelutils() {

		return new Object[][] {{1,2,3},{2,3,4}};
		
	}
	
	
	@DataProvider
	public Object[] userdata() {
		return excelutils();

	}

	@Test(priority = 0, dataProvider = "userdata", enabled = false)
	
	public void validateGetUser(int dataID, int max,  int slow) {
		ass = new SoftAssert();

		response = given()
				.spec(requestSpec)
				.when()
				.get("/api/users/"+dataID)
				.then()
				.body("data.id", equalTo(dataID)) // to check the data id
				.statusCode(200)	 // to check the status code of creation, either 201 or 200
			//	.body("data.email", notNullValue()) // to check if the email value is null
			//	.body("data.first_Name", notNullValue()) // to check if the 
				.extract() //
				.response();

		String firstName = response.jsonPath().getString("data.first_name");
		ass.assertEquals(firstName, "Emma", "\nThe Error is that"+"The firstname of the index ["+dataID+"] is not matched : ");
		ass.assertEquals("data.first_name", notNullValue(),"not null");
		ass.assertAll();


	}

	@Test(priority = 1, enabled = false)
	public void validateUserTest() {
		String payload = PayloadManager.getCreateUserPayload("Joohn", "QA");

		given().spec(requestSpec)
		.body(payload)
		.when()
		.post("/api/users")
		.then()
		.statusCode(201)
		.body("name",equalTo(PayloadManager.payload.getString("name")));

	}
	
	@AfterTest
	public void tearDown() {
		
	
	}
	
	
	
	//=-------------------------------------------------------
	
	
	
	
	@Test
	public void validateGetUsersPage2() {
        // Base URI
        

        // Perform GET request
         response = given()
        		 .spec(requestSpec)
                .queryParam("page", 2)
                .when()
                .get("/api/users")
                .then()
                .log().all()
                .extract().response();

        // ✅ 1. Validate Status Code
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        // ✅ 2. Validate Content-Type
        String contentType = response.getHeader("Content-Type");
        assertTrue(contentType.contains("application/json"), "Content-Type should include 'application/json'");

        // ✅ 3. Validate data array is present
        assertTrue(response.jsonPath().getList("data").size() > 0, "`data` array should not be empty");

        // ✅ 4. Validate each user has `id` and `email`
        response.jsonPath().getList("data").forEach(userObj -> {
            var map = (java.util.Map<?, ?>) userObj;
            assertTrue(map.containsKey("id"), "Each user should have an id");
            assertTrue(map.containsKey("email"), "Each user should have an email");
        });

        // ✅ 5. Response Time
        long time = response.getTime();
        assertTrue(time < 2000, "Response time should be < 2000ms");
    }
	
	
	
	
	

}
