package ReqRes_TestCase;

import org.testng.Assert;
import org.testng.annotations.*;
import ReqRes_Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC_001_GetAllUsers extends TestBase {



	@BeforeClass
	public void GetAllUsers() throws InterruptedException 
	{
		logger.info("*************Started TC_001_Get All Users*************");
		RestAssured.baseURI = "https://reqres.in/api";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET,"/users?page=2");
		Thread.sleep(4000);

	}

	@Test
	public void checkResponseBody() 
	{
		logger.info("*************Checking Response Body*************");
		String responseBody = response.getBody().asString();
		logger.info("Response body ===>"+responseBody);
		Assert.assertTrue(responseBody!=null);		
	}

	@Test
	public void checkStatusCode()
	{
		logger.info("*************Checking Status Code*************");
		int statuscode = response.statusCode();
		logger.info("Status Code is ===>"+statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	public void checkResponseTime() 
	{
		logger.info("*************Checking Response Time*************");
		long responseTime = response.getTime();
		logger.info("Response time is ===>"+responseTime);
		if (responseTime>=2000) 
		{
			logger.warn("Response Time is greater than 2000 milliseconds");
			Assert.assertTrue(responseTime<10000);
		}		
	}


	@Test
	public void checkStatusLine() 
	{
		logger.info("*************Checking Status Line*************");
		String statusLine = response.statusLine();
		logger.info("Status line is ==>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkContentType() 
	{
		logger.info("*************Checking Content Type*************");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	
	@AfterClass
	public void teardown() 
	{
		logger.info("*************Finished TC_001_Get All Usersr*************");
	}
}
