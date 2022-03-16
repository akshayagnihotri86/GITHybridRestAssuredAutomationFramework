package ReqRes_Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;



public class Listners_Extent extends TestListenerAdapter {

	public ExtentSparkReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;



	@Override
	public synchronized void onStart(ITestContext testContext) {
		htmlreporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/myreport.html");

		htmlreporter.config().setDocumentTitle("Automation Report");
		htmlreporter.config().setReportName("REST REQ|RES API Testing Report");
		htmlreporter.config().setTheme(Theme.DARK);

		extent= new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Name", "Local Machine Run");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Akshay");
	}



	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		
		test= extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case Passed is " +result.getName());
		
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case Failed is " +result.getName());
		test.log(Status.FAIL, "Test Case Failed is " +result.getThrowable());
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		test= extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case Skipped is " +result.getName());
		
	}
	
	@Override
	public synchronized void onFinish(ITestContext testcontext) {
		
		extent.flush();
	}

	
}
