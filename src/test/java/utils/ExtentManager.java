package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/*
 ExtentManager creates and manages Extent Report instance.
*/

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            // Report file path
            String path = System.getProperty("user.dir")
                    + "/reports/ExtentReport.html";

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(path);

            reporter.config().setReportName("Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();

            extent.attachReporter(reporter);
        }

        return extent;
    }
}