package tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class TypicalAPI {


	@Test
	public void test() {
    RestAssured.baseURI = "https://regqc.sifyitest.com";

    given()
        .multiPart("selpost", "01")
        .multiPart("opt_cat", "EWS")
        .multiPart("certificate_no", "iuhb")
        .multiPart("certificate_dateday", "01")
        .multiPart("certificate_datemon", "06")
        .multiPart("certificate_dateyear", "2025")
        .multiPart("optdisability", "N")
        .multiPart("optdisability_40less", "N")
        .multiPart("religion", "Buddhist")
        .multiPart("txtnationality", "Indian")
        .multiPart("selexamcentre1", "12")
        .multiPart("centrecode1", "12")
        .multiPart("seldobday", "01")
        .multiPart("seldobmon", "01")
        .multiPart("seldobyear", "1995")
        .multiPart("optsex", "O")
        .multiPart("mstatus", "Married")
        .multiPart("fatherfirstname", "iuhg")
        .multiPart("motherfirstname", "huh")
        .multiPart("txtaddress1", "ghu")
        .multiPart("txtdistrict", "h")
        .multiPart("txtstate", "10")
        .multiPart("txtpin", "187654")
        .multiPart("acc_holder_name", "iuhghji")
        .multiPart("bankaccno", "09")
        .multiPart("c_acc_no", "09")
        .multiPart("ifsc", "oihbhjijhbn")
        .multiPart("myear", "1993")
        .multiPart("submit_type", "submit2")
        .multiPart("q", "ZmNiODEwNDk2MmVjM2EwMWE1ZmRiNmU0ZTY5MWI5YmZ8ODAwMDE0") // from URL
        .multiPart("close_date", "31.07.2025")
        .multiPart("close_year", "2025")
        .multiPart("close_month", "07")
        .multiPart("close_day", "31")
        .multiPart("val", "1")
        .multiPart("gval", "1")
        .multiPart("ps", "ZWVkZjQyYTQ4NjdmN2E0NTE1ODBhZmUwMzgwNTI5YWN8MTc1MjMzMTk5OQ==")
        .multiPart("t", "NzYxYzFkYzgxNjJhMzQ5MjhmOWMxNWZhYzcxNDY1YWV8MTc1MjMzMTk4MA==")
    .when()
        .post("/rbijun25/all_submit.php")
    .then()
        .statusCode(302)  // or 302/redirect depending on server behavior
        .log().all();
	}
}
