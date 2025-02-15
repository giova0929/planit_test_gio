package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private int passedTests = 0;
    private int failedTests = 0;

    @Override
    public void onTestSuccess(ITestResult result) {
        passedTests++;
        System.out.println("✅ :) TEST PASS : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedTests++;
        System.out.println("❌ ERROR: TEST FAILED  → " + result.getName());
        System.out.println("📝 error message:  " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n===============================");
        System.out.println("📊 Test Report Gio:");
        System.out.println("✅ Pass: " + passedTests);
        System.out.println("❌ Fail: " + failedTests);
        System.out.println("===============================");
    }
}
