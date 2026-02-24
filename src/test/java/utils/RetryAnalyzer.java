package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    // Counter to track retry attempts
    private int count = 0;

    // Maximum retry attempts
    private static final int maxTry = 2;

    @Override
    public boolean retry(ITestResult result) {

        // If retry count is less than max allowed
        if (count < maxTry) {

            count++;   // increase retry counter

            System.out.println("Retrying Test: "
                    + result.getName()
                    + " | Attempt: " + count);

            return true;   // re-run test
        }

        return false;  // do not retry anymore
    }
}