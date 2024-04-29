package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportClass;

public class Listener extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportClass.getReport();
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		thread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		thread.get().log(Status.PASS, "Test suceess");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		thread.get().log(Status.FAIL, "Test failed");
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			test.addScreenCaptureFromPath(takeScreenshot(result.getMethod().getMethodName(), driver));
		} catch (IOException e) {
			e.printStackTrace();
		}
		thread.get().fail(result.getThrowable());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		thread.get().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
