package com.selenium.eduviya_utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportListener implements ITestListener {
	public static ExtentReports extent;
	public static ExtentTest test;
    public ExtentSparkReporter htmlReporter;
    @Override
    public void onStart(ITestContext context) {
    	
        htmlReporter = new ExtentSparkReporter("report/" + timestamp() + "myreport.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Test Report");
 
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Tester");
    }
 
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }
 
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }
 
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }
 
    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }
 
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}
