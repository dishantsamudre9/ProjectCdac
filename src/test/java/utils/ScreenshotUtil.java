package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	public static String captureScreenshot(WebDriver driver, String testName) {

	    if (driver == null) {
	        System.out.println("Driver is null — screenshot skipped");
	        return null;
	    }

	    TakesScreenshot ts = (TakesScreenshot) driver;

	    File src = ts.getScreenshotAs(OutputType.FILE);

	    String path = System.getProperty("user.dir")
	            + "/reports/"
	            + testName + "_"
	            + Thread.currentThread().threadId() + "_"
	            + System.currentTimeMillis()
	            + ".png";

	    File dest = new File(path);

	    try {
	        FileUtils.copyFile(src, dest);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return path;
	}

}
