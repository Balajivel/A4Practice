package org.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;



public class ReqResAssessment {
	
	ExtentTest test;
	ExtentReports extentReports;
	
	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("extentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		
		test = extentReports.createTest("Sauce Labs Test", "Functionality Validation");

	}
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in/api";
		
		test.log(Status.INFO, "Starting test case for Req Res site.");
		
	}
	
	@Test
	public void getList() {
		Response response = RestAssured.get("/users?page=2");
		assertEquals(response.getStatusCode(), 200);
		assertTrue(response.getBody().asString().contains("data"));
		
		test.pass("Retrived Data from List of GET Method");

	}
	
	@Test
	private void getSingle() {
		Response response = RestAssured.get("/users/2");
		assertEquals(response.getStatusCode(), 200);
		assertTrue(response.getBody().asString().contains("data"));
		assertTrue(response.getBody().asString().contains("id"));
		assertTrue(response.getBody().asString().contains("email"));
		
		test.pass("Retrived Data from Single GET Method");

	}
	
	@Test
	public void postUser() {
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		Response response = RestAssured.given().contentType("application/json").body(requestBody).when().post("/users");
		assertEquals(response.getStatusCode()	, 201);
		assertTrue(response.getBody().asString().contains("id"));
		assertTrue(response.getBody().asString().contains("createdAt"));
		
		test.pass("Created Data for User using POST method");
	}
	
	@AfterSuite
	public void ending() {
		extentReports.flush();

	}
	

}
