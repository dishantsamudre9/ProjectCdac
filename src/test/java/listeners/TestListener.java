package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

	ExtentReports extent = ExtentManager.getInstance();

	// Thread safe ExtentTest
	ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());

		test.set(extentTest); // store per thread
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = base.DriverFactory.getDriver();

		String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());

		if (path != null) {
			try {
				test.get().addScreenCaptureFromPath(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		test.get().fail(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}
}