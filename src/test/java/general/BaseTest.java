package general;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.venturedive.base.database.connection.SonarDB;
import com.venturedive.base.exception.APIException;
import com.venturedive.base.model.ExecutionStats;
import com.venturedive.base.utility.JIRA;
import com.venturedive.base.utility.MessagesIntegration;
import com.venturedive.base.utility.SendEmailAfterExecution;
import com.venturedive.base.utility.TestRail;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import javax.mail.MessagingException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static config.ConfigProperties.*;

public class BaseTest  {

    ExtentTest logger;

    //    For Reporting to insert into database
    static Date startTime = null;
    static Date endTime = null;
    static Integer passedCount = 0;
    static Integer failedCount = 0;
    static Integer skippedCount = 0;

    SonarDB dbconn= new SonarDB();
    //    For Reporting to insert into database
    static ArrayList<String> automationSteps;
    static Integer beforeAddingStepsLength;
    static Integer afterAddingStepsLength;
    static Integer beforeAddingExpectedResultLength;
    static Integer afterAddingExpectedResultLength;
    static ArrayList<String> expectedResults;
    static ArrayList<File> screenShotCollection = new ArrayList<File>();


    @BeforeSuite
    public void beforesuite(ITestContext ctx) throws SQLException, IOException, AWTException, APIException {

      //  MainCall.preReq.setup();
        startReport();
        if(IsEnableRecording.equals("true"))
            Recorder.deleteRecordings();
        WebDriverManager.chromedriver().setup();

       // MainCall.webDriverFactory.getInstance();
        automationSteps = new ArrayList<String>();
        expectedResults=new ArrayList<String>();
    }
    public void startReport() {
        if (IsEnableReporting.equals("true")) {
            MainCall.startReport();
        }
        startTime = getTime(); // For reporting into db
    }

    @BeforeMethod
    public void beforeTest(Method method) throws Exception {

        beforeAddingStepsLength=automationSteps.size();
        beforeAddingExpectedResultLength=expectedResults.size();
        System.out.println("before"+beforeAddingStepsLength);
        System.out.println("before"+beforeAddingExpectedResultLength);

        if(IsEnableRecording.equals("true"))
            Recorder.startRecording(method.getName());

        if(IsEnableReporting.equals("true")) {
            logger = MainCall.getExtentReport().startTest(method.getName(), "");
            logger.setStartedTime(getTime());
        }
    }

    @AfterMethod
    public void QuitDriver(ITestResult result, ITestContext ctx, Method method) throws Exception {
        afterAddingStepsLength=automationSteps.size();
        afterAddingExpectedResultLength=expectedResults.size();
        if(IsEnableReporting.equals("true")) {
            if (result.getStatus() == ITestResult.FAILURE) {
                failedCount++;
                logger.log(LogStatus.FAIL, "Test Case Failed reason is: " + result.getThrowable());
                logger.log(LogStatus.INFO, "StackTrace Result: " + Arrays.toString(result.getThrowable().getStackTrace()));
                logger.log(LogStatus.FAIL, logger.addScreenCapture(Screenshots.takeScreenshot(result.getMethod().getMethodName())));
                screenShotCollection.add(Screenshots.screenShot);
                if (LogTestRail.equals("true")) {
                    JIRA.CreateJiraWithScreenShot(result, Screenshots.screenShot, beforeAddingStepsLength, afterAddingStepsLength, automationSteps);
                    JIRA.PostMobileIssuesJira();
                }
            } else if (result.getStatus() == ITestResult.SKIP) {
                skippedCount++;
                logger.log(LogStatus.SKIP, "Test Case Skipped is: " + result.getName());
            } else {
                passedCount++;
                logger.log(LogStatus.PASS, result.getMethod().getMethodName() + " is Passed");
            }

            logger.setEndedTime(getTime());
            MainCall.getExtentReport().endTest(logger);
        }
        if(IsEnableRecording.equals("true"))
            Recorder.stopRecording();
        TestRail.getCaseIdandResultmobile(result,ctx,method,beforeAddingStepsLength,afterAddingStepsLength,automationSteps,beforeAddingExpectedResultLength,afterAddingExpectedResultLength,expectedResults,null);
        }


    private Date getTime(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    @AfterSuite
    public void tearDown() throws SQLException, APIException, IOException, MessagingException {
        endTime = getTime();
        dbconn.insertReportingDataIntoDB(startTime, passedCount, failedCount, skippedCount, startTime, endTime); //need to open after jira integration
        SendEmailAfterExecution.sendReportAfterExecution(passedCount, failedCount, skippedCount);
        MainCall.webDriverFactory.finishDriver();
        if(IsEnableReporting.equals("true")) {
            MainCall.getExtentReport().flush();
            MainCall.getExtentReport().close();
        }
        if (LogTestRail.equals("true")) {
            System.out.println(LogTestRail);
           TestRail.createSuite();
           TestRail.updateTestRail();
           TestRail.AttachImagesWithTestResults(screenShotCollection);
        }

        ExecutionStats executionStats = new ExecutionStats();
        executionStats.passed = passedCount;
        executionStats.failed = failedCount;
        executionStats.skipped = skippedCount;
        executionStats.reportURL = MainCall.reportPath;
        executionStats.executionDateTime = endTime;
        try {
            MessagesIntegration.sendStatsToWorkspace(executionStats);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
