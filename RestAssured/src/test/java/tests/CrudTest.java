package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basetest.BaseTest;
import io.restassured.response.Response;
import utils.UserDeserialize;
import utils.DataDeserializer;
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

	@Test(priority = 0, dataProvider = "userdata", enabled = false, groups= {"sanity"})
	
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

	@Test(priority = 1, enabled = false, groups= {"sanity"})
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
	
	
	
	
	@Test(groups = {"positive", "smoke"})
	public void validateGetUsersPage2() {
 
        
		
		        try {
		        
		            logger.logInfo("=== Sending GET request to /api/users?page=2 ===");

		            Response response = given()
		                    .spec(requestSpec)
		                    .queryParam("page", 2)
		                    .when()
		                    .get("/api/users")
		                    .then()
		                    .log().all() // This logs REST Assured response only
		                    .extract().response();
		            logger.logInfo("Received response with status code: {"+response.getStatusCode()+"} " );
		            logger.logInfo("Received response with status code: {"+response.getStatusCode()+"} ");
		            logger.logDebug("Content-Type header: {"+response.getHeader("Content-Type")+"} " );
		            logger.logDebug("Response time (ms): {"+response.getTime()+"} ");
		            logger.logDebug("Response body:\n{"+response.print()+"} " );

		            // ✅ 1. Validate Status Code
		            assertEquals(response.getStatusCode(), 200, "Status code should be 200");
		            logger.logInfo("✅ Status code validated successfully");

		            // ✅ 2. Validate Content-Type
		            String contentType = response.getHeader("Content-Type");
		            assertTrue(contentType.contains("application/json"), "Content-Type should include 'application/json'");
		            logger.logInfo("✅ Content-Type validated successfully");

		            // ✅ 3. Validate data array is present
		            int dataSize = response.jsonPath().getList("data").size();
		            assertTrue(dataSize > 0, "`data` array should not be empty");
		            logger.logInfo("✅ Data array size: {"+dataSize+"} "  );

		            // ✅ 4. Validate each user has `id` and `email`
		            response.jsonPath().getList("data").forEach(userObj -> {
		                var map = (java.util.Map<?, ?>) userObj;
		                assertTrue(map.containsKey("id"), "Each user should have an id");
		                assertTrue(map.containsKey("email"), "Each user should have an email");
		            });
		            logger.logInfo("✅ Each user has 'id' and 'email'");

		            // ✅ 5. Response Time
		            long time = response.getTime();
		            assertTrue(time < 2000, "Response time should be < 2000ms");
		            logger.logInfo("✅ Response time validated: {"+time+" ms }");

		        } catch (AssertionError ae) {
		            logger.logError("❌ Assertion failed: {"+ae.getMessage()+"}"  ,ae);
		            throw ae;
		        } catch (Exception e) {
		            logger.logError("❌ Test execution error: {"+ e.getMessage()+"}", e);
		            throw e;
		        }
		    }
	

	 @Test(groups = "negative")
	    public void getInvalidEndpoint_shouldReturn404() {
	        logger.logRequest("GET /api/userz?page=2");

	         response = given()
	        		 .spec(requestSpec)
	                    .queryParam("page", 2)
	                .when()
	                    .get("/api/userz")
	                .then()
	                    .log().all()
	                    .extract().response();

	        int statusCode = response.getStatusCode();
	        String body = response.asString();

	        logger.logResponse("Status: " + statusCode);
	        logger.logDebug("Body: " + body);

	        assertEquals(statusCode, 404, "Expected status 404 for invalid endpoint");
	        assertTrue(body.contains("Not Found") || body.isEmpty(), "Expected 'Not Found' message or empty body");
	    }
	 
	 @Test(groups= {"smoke","positive"})
	    public void createUser() {
	        logger.logInfo("🟢 Starting Create User Test");

	        String payload = PayloadManager.getCreateUserPayload("Joohn", "Engineer");
	        logger.logRequest("POST to /api/users/ " + payload);

	        given()
	            .spec(requestSpec)
	            .body(payload)
	        .when()
	            .post("/api/users")
	        .then()
	            .statusCode(201)
	            .body("name", equalTo("Joohn"))
	            .body("job", equalTo("Engineer"))
	            .body("id", notNullValue())
	            .body("createdAt", notNullValue());

	        logger.logInfo("✅ User creation test passed");
	    }
	 
	 
	 @Test(groups = {"smoke", "positive"})
	 public void updateUser() {
	     logger.logInfo("🟡 Starting Update User Test");
	     String endPoint = "/api/users/2";

	     String payload = PayloadManager.getCreateUserPayload("Jookopphn", "Manager");
	     logger.logRequest("UPDATE paylod of "+payload);
	     logger.logInfo("Endpoint: {" + endPoint+"}");

	     given()
	         .spec(requestSpec)
	         .body(payload)
	     .when()
	         .put(endPoint)
	     .then()
	         .statusCode(200)
	         .body("name", equalTo(PayloadManager.payload.get("name")))
	         .body("job", equalTo(PayloadManager.payload.get("job")));

	     logger.logInfo("✅ User update test passed");
	 }
	 
	 
	 @Test(priority =10, groups= {"sanity"})
	 public void deserialize() {
		 
		 response = given()
				 .spec(requestSpec)
	                .when()
	                .get("/api/users/1")
	                .then()
	                .statusCode(200)
	                .extract()
	                .response();
		 
		 DataDeserializer data = response.as(DataDeserializer.class);
		 System.out.println("ID is : "+ data.getData().getId());
		 
	 }


}
		

     
	
	
	

	


