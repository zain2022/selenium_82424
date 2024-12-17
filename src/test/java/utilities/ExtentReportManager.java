package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ExtentReportManager implements ITestListener { // Implementing Predefined Interface in Testng

    public ExtentSparkReporter sparkReporter; // Responsible for the look and feel of the report
    public ExtentReports extent; // Used to specify the common information
    public ExtentTest test; // Used to create entries for pass/fail/skip test cases
    String repName;

    public void onStart(ITestContext testContext){

        // Deleting previously generated Extent Reports from reports directory
//        Arrays.stream(new File(".\\reports\\").listFiles()).forEach(File::delete);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Time Stamp
        repName = "Test-Report-"+timeStamp+".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); // Specify loaction of the report

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of the report
        sparkReporter.config().setReportName("Pet Store Users API"); // Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Selenium Objective");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Usewr Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Zain");

    }

    public void onTestSuccess(ITestResult result){

        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, "Test Passed");
    }
    public void onTestFailure(ITestResult result){

        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());

    }
    public void onTestSkipped(ITestResult result){

        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());

    }
    public void onFinish(ITestContext testContext){
        extent.flush();
    }

}
