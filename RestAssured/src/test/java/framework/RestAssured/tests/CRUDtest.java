package framework.RestAssured.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import framework.RestAssured.basetest.BaseTest;
import framework.RestAssured.utils.PayloadManager;
import io.restassured.response.Response;

public class CRUDtest extends BaseTest {

	
	
	
	SoftAssert ass = new SoftAssert();
	Response response;

	public Object[][] excelutils() {

		return new Object[][] {{1,2,3},{2,3,4}};
	}
	
	
	@DataProvider
	public Object[] userdata() {
		return excelutils();

	}

	@Test(priority = 0, dataProvider = "userdata")
	public void validateGetUser(int userdata, int max,  int slow) {

		response = given()
				.spec(requestSpec)
				.when()
				.get("/api/users/"+userdata)
				.then()
				.statusCode(200)
				.extract()
				.response();

		String firstName = response.jsonPath().getString("data.first_name");
		Assert.assertEquals(firstName, "Emma", "The firstname of the index ["+userdata+"] is not matched : ");


	}

	@Test(priority = 1)
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

}
