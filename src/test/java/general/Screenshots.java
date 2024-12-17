package general;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static general.MainCall.webDriverFactory;

public class Screenshots {
    public static File screenShot;
    public static  String filePath;

    public static final String takeScreenshot(String test) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh-mm-ss"); //windows not support colon (Bug fixing for windows users)
        Calendar now = Calendar.getInstance();

        screenShot  = ((TakesScreenshot)webDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

        filePath = System.getProperty("user.dir") + "/screenshots/" + test + "_" + formatter.format(now.getTime())+".jpg";

        try {
            FileUtil.copyFile(screenShot, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        filePath = filePath.replace(System.getProperty("user.dir"), "..");
        return filePath;

    }
    public static String getScreenshot(String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) WebDriverFactory.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName + ".jpg";

        File finalDestination = new File(destination);
        FileUtil.copyFile(source, finalDestination);

        return destination;
    }
}
