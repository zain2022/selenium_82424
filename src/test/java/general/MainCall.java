package general;

import com.relevantcodes.extentreports.ExtentReports;
import com.venturedive.base.database.connection.DbOperation;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import objects.*;
import preReq.TestBase;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static config.ConfigProperties.BaseURI;
import static config.ConfigProperties.Environment;

public class MainCall {

    static ExtentReports extent;
    public final static String reportPath = System.getProperty("user.dir") + "\\reports\\ExtentReport-"+ new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss").format(Calendar.getInstance().getTime())+".html";


    public static ExtentReports startReport()
    {
        //ExtentReports(String filePath,Boolean replaceExisting)
        //filepath - path of the file, in .htm or .html format - path where your report needs to generate.
        //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
        //True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
        //False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
        extent = new ExtentReports(System.getProperty("user.dir") + "/reports/ExtentReport.html", true);

        //extent.addSystemInfo("Environment","Environment Name")
        extent.addSystemInfo("Environment", Environment);

        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
        extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));

        return extent;
    }
    public final static LoginPage loginPage = new LoginPage();
    public final static GenericFunctions genericFunctions = new GenericFunctions();
    public final static EnvGlobals envGlobals = new EnvGlobals();
    public final static WebDriverFactory webDriverFactory = new WebDriverFactory();
    public final static TrainingSessionPage trainingSessionPage = new TrainingSessionPage();
    public final static WebDriverWaits webDriverWaits = new WebDriverWaits();
    public final static  DbOperation dbOp = new DbOperation();
    public final static TestBase preReq = new TestBase();
    public final static CommonAssertions log = new CommonAssertions();
    





    public static ExtentReports getExtentReport()
    {
        if (extent != null) {
            return extent;
        } else {
            throw new IllegalStateException("Extent Report object not initialized");
        }
    }

    public static void setupPreReqs() throws SQLException {
        //Rest Assured config
        RestAssured.baseURI = BaseURI;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        RestAssured.useRelaxedHTTPSValidation();
        TestBase.setup();
    }


    public static void restAssuredPreReq() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);

    }



}
