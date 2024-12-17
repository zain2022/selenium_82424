package general;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class WebDriverWaits {
    private static WebDriver jsWaitDriver;
    private static WebDriverWait jsWait;
    private static JavascriptExecutor jsExec;
    public static WebDriverWait wait ;


    public static WebElement waitUntilElementIsClickable(By locator)
    {
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return webElement;
    }

    public static void visibilityOf(By element){
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void invisibilityOf(By locator){
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
         wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void visibilityOfMainBoard(){
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
         wait.until(ExpectedConditions.visibilityOf(WebDriverFactory.getDriver().findElement(By.id("mainboard"))));
    }
    public static void visibilityOfSchematicView(){
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
         wait.until(ExpectedConditions.visibilityOf(WebDriverFactory.getDriver().findElement(By.id("schematicView"))));
    }

    public static WebElement waitUntilElementNotDisplayed(final WebElement webElement) {
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), TIMEOUT);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return true;
                }
                catch (NoSuchElementException e ) {
                    return false;
                }
            }
        };
        wait.until(elementIsDisplayed);
        return webElement;
    }


    public static void waitForPageReadyByJquery() {
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
        int counter = 0;
        if(!(Boolean) ((JavascriptExecutor) WebDriverFactory.getDriver()).executeScript("return (window.jQuery != null) && (jQuery.active === 0);"))
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void waitUntilElementVisible(){
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
        new WebDriverWait(WebDriverFactory.getDriver(), 50).until(ExpectedConditions.invisibilityOfElementLocated(By.className("vj-animated-svg-icon"))); // wait for Loading panel to close
    }

    public static void waitUntilLoaderDisapears(){
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
        WebElement loader = WebDriverFactory.getDriver().findElement(By.className("spinner-wrapper"));

        while(loader.getAttribute("style").contains("block")){
            WebDriverWaits.sleep(2000);
        }
    }

    public static void waitUntilRoleSelected(WebElement role){
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);
//        wait.until(ExpectedConditions.attributeContains(role, "class", "SettingsButton-ListItem"));
        wait.until(ExpectedConditions.elementSelectionStateToBe(role, true));
    }

    public static void sleep(int time){

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleep250(){
        sleep(250);
    }
    public static void sleep500(){
        sleep(500);
    }
    public static void sleep1000(){
        sleep(1000);
    }

    public static boolean waitForElToBeAppeared(String element) {
        try {
            WebDriverFactory.getDriver().manage().timeouts()
                    .implicitlyWait(0, TimeUnit.SECONDS);

            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 1000, 7000);

            return wait
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }


    public static WebElement waitFor(ExpectedCondition<WebElement> webElementExpectedCondition) {
        wait =new WebDriverWait(WebDriverFactory.getDriver(),20);

        return wait.until(webElementExpectedCondition);
    }
}
