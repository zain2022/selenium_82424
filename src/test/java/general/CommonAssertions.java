package general;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;

import java.io.File;

import static general.BaseTest.automationSteps;
import static general.BaseTest.expectedResults;

public class CommonAssertions {
    private   static org.apache.log4j.Logger log= org.apache.log4j.Logger.getLogger(Logger.class);

    public String log4jConfigFile = System.getProperty("user.dir")
            + File.separator + "log4j2.xml";

    public CommonAssertions()
    {
        DOMConfigurator.configure(log4jConfigFile);
        log.setLevel(Level.INFO);
    }

    public static void logInfo(String Comment)
    {
        log.info(Comment);


    }
    public static void logActualReult(String Comment)

    {
        log.info(Comment);
        automationSteps.add(Comment);

    }
    public static void logExpectedResult(String Commeent)
    {

        log.info(Commeent);
        expectedResults.add(Commeent);

    }
    public static void logVerifyTrue(String Comment ,Boolean expected )
    {


        Assert.assertTrue(expected,Comment);
        log.info(Comment);
    }

    public static void logVerifyFalse(String Comment ,Boolean expected )
    {

        Assert.assertFalse(expected,Comment);
        log.info(Comment);


    }
    public static void logVerifyIntEqual(int actual,int expected , String comment)
    {

        Assert.assertEquals(actual,expected,comment);
        logInfo(comment);
    }
    public static void logVerifyStringEqual(String actual,String expected , String comment) {

//        try {
        Assert.assertEquals(actual, expected, comment);
        logInfo(comment);
//        }
//        catch (AssertionError e)
//        {
//            log.error("Assertion Failure",e);
//            Assert.fail();
//        }
    }
    public static void logVerifyStringNotEqual(String actual, String expected, String comment)
    {
        Assert.assertNotEquals(actual,expected,comment);
        logInfo(comment);
    }


    public static void logVerifyGreaterThan(String comments, int actual,int expected)
    {


        Assert.assertTrue(actual>=expected, comments);
        logInfo(comments);
    }


}
