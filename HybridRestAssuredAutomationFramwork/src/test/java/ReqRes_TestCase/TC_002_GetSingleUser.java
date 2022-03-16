package ReqRes_TestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ReqRes_Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_002_GetSingleUser extends TestBase {

	RequestSpecification httprequest;
	Response response;

	@BeforeClass
	public void getUser() throws Exception 
	{
		logger.info("*************Started TC_002_Get User*************");
		RestAssured.baseURI = "https://reqres.in/api";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET,"/unknown/"+empid);
		Thread.sleep(2000);
	}

	@Test
	public void checkResponseBody() 
	{
		logger.info("*************Checking Response Body*************");
		String responseBody = response.getBody().asString();
		logger.info("Response body ===>"+responseBody);
		Assert.assertTrue(responseBody!=null);	
		Assert.assertEquals(responseBody.contains(empid), true);
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

	@Test
	public void checkStatusCode()
	{
		logger.info("*************Checking Status Code*************");
		int statuscode = response.statusCode();
		logger.info("Status Code is ===>"+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	@Test
	public void checkServerType() 
	{
		logger.info("*************Checking Server Type*************");
		String serverType = response.header("Server");
		logger.info("Server Type is ==>"+serverType);
		System.out.println("Server Type is ==>"+serverType);
		Assert.assertEquals(serverType, "cloudflare");
	}
	
	
	@Test
	public void checkContentLength() 
	{
		logger.info("*************Checking Content Length*************");
		String contentLength = response.header("Content-Length");
		System.out.println("Content length is ==>"+contentLength);
		//Assert.assertTrue(Integer.parseInt(contentLength)<1500);
		
	}
	

	@AfterClass
	public void teardown() 
	{
		logger.info("*************Finished TC_002_Get User*************");
	}
}
