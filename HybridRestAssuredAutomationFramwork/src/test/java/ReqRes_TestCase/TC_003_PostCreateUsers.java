package ReqRes_TestCase;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.JsonObject;

import ReqRes_Base.TestBase;
import ReqRes_Utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_003_PostCreateUsers extends TestBase {

	RequestSpecification httprequest;
	Response response;

	String empname = RestUtils.empName();
	String emprole = RestUtils.empRole();

	@BeforeClass
	public void createUsers() throws InterruptedException {
		logger.info("*************Started TC_003_Post Create Users*************");
		RestAssured.baseURI="https://reqres.in/api";
		httprequest = RestAssured.given();

		JSONObject stringparams = new JSONObject();
		stringparams.put("name",empname);
		stringparams.put("job",emprole);

		httprequest.header("Content-Type","application/json");
		httprequest.body(stringparams.toJSONString());
		response = httprequest.request(Method.POST,"/users");
		Thread.sleep(3000);
	}

	@Test
	public void checkResponseBody()
	{

		logger.info("*************Checking Response Body*************");
		String responseBody = response.getBody().asString();
		logger.info("Response body ===>"+responseBody);

		Assert.assertEquals(responseBody.contains(empname), true);
		Assert.assertEquals(responseBody.contains(emprole), true);
	}

	@Test
	public void checkStatusCode()
	{
		logger.info("*************Checking Status Code*************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>"+statusCode);
		Assert.assertEquals(statusCode,201);			
	}

	@Test
	public void checkStatusLine()
	{
		logger.info("*************Checking Status Line*************");
		String statusLine = response.getStatusLine();
		logger.info("Status line is ==>"+statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 201 Created");

	}
	@AfterClass
	public void teardown() 
	{
		logger.info("*************Finished TC_003_Post Create User*************");
	}


}
