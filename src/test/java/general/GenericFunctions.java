package general;


import com.mysql.cj.exceptions.AssertionFailedException;
import io.percy.selenium.Percy;
import objects.CommonLocators;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static general.WebDriverFactory.action;
import static org.apache.commons.lang3.StringUtils.substringAfter;


public class GenericFunctions {

    public static String generateRandomNum(int length)
    {
        String RawRandomNumber = RandomStringUtils.randomNumeric(length);
        return RawRandomNumber;
    }

    public static List<Integer> generateRandomNumSystem(int length, int sysCount)
    {
        List<Integer> RawRandomNumber = new ArrayList<>();
        for (int i=0 ; i<sysCount ; i++) {
            RawRandomNumber.add(Integer.valueOf(RandomStringUtils.randomNumeric(length)));
        }
        return RawRandomNumber;
    }

    public static String generateAlphaNumeric(String s, int length)
    {
        String RawRandomNumber = RandomStringUtils.randomNumeric(length);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s);
        stringBuilder.append(RawRandomNumber);
        return stringBuilder.toString();
    }

    public static String RandomPhoneNumber(String RawRandomNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+92300");
        stringBuilder.append(RawRandomNumber);
        return stringBuilder.toString();
    }

    public static String generateRandomString(int length) {
        String name = RandomStringUtils.randomAlphabetic(length);
        String first_letter = name.substring(0, 1).toUpperCase();
        String other_letters = name.substring(1).toLowerCase();
        String finalname = first_letter + other_letters;
        return finalname;
    }

    public static String generateEmail(String finalname)
    {
        return finalname + "@vd.com";
    }

    public static void clearFieldWithJS(By locator, String input) {
        JavascriptExecutor js = (JavascriptExecutor)WebDriverFactory.getDriver();
        String element = substringAfter(locator.toString(), ": ");

        js.executeScript("document.getElementById('" + element + "').value = '" + input + "'");
    }

    public static void mouseHover(By by){
//        action = new Actions(WebDriverFactory.getDriver());
        action.moveToElement(WebDriverFactory.getDriver().findElement(by))
                .perform();
    }

    public static int stringToInt(String number) {
        int result = Integer.parseInt(number);
        return result;
    }

    public void clickElementByText(String text) throws InterruptedException {
        WebDriverFactory.getDriver().findElement(By.xpath("//*[contains(text(), '" + text + "')]")).click();
        Thread.sleep(1000);
    }

    public int getColumnsName(String columnName)
    {
        int i = 0;
        List<WebElement> elements11 = WebDriverFactory.getDriver().findElement(CommonLocators.byThead).
                findElements(CommonLocators.byTr).get(1).findElements(CommonLocators.byTh);

        for (WebElement element: elements11) {
            if (element.getText().trim().equals(columnName))
            {
                break;
            }
            i++;
        }
        return i;
    }

    public List<WebElement> getRowsOfTable() {
        return WebDriverFactory.getDriver().findElement(CommonLocators.byTable).findElements(CommonLocators.byTr);

    }

    public void clickOnColumnOfTable(String itemName) {
        List<WebElement> Rows = getRowsOfTable();
        for (int i = 0; i <= Rows.size(); i++) {
            List<WebElement> Columns = WebDriverFactory.getDriver().findElements(CommonLocators.byTd);
            for (WebElement Column : Columns)
                if (Column.getText().trim().equals(itemName)) {
                    Actions actions = new Actions(WebDriverFactory.getDriver());
                    actions.click(Column).build().perform();
                    break;
                }
        }
        WebDriverWaits.waitUntilLoaderDisapears();
    }

    public void scrollUp() {
        WebElement element = WebDriverFactory.getDriver().findElement(By.className("locator"));
        ((JavascriptExecutor) WebDriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true)", element);

    }

    public static void PercyCapture(String Name) throws InterruptedException {
        Percy percy = new Percy(MainCall.webDriverFactory.getDriver());
        Thread.sleep(2000);
        percy.snapshot(Name);
        Thread.sleep(2000);
    }
    public static void assertion(int actual,int expected)
    {

        Assert.assertEquals(actual,expected);
    }
    public static void assertion(By locator,String expected)
    {
        try {
            MainCall.webDriverWaits.visibilityOf(locator);
            {
                String text = WebDriverFactory.getDriver().findElement(locator).getText();
                Assert.assertEquals(text, expected);
            }
        }
        catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", locator));
        }

    }

    public static void driverStart(String url)
    {
        WebDriverFactory.getDriver().get(url);
        WebDriverFactory.getDriver().manage().window().maximize();

    }

    public static void scrollToElement(By locator)
    {
        try {
            MainCall.webDriverWaits.visibilityOf(locator);
            {
                ((JavascriptExecutor) WebDriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView();", WebDriverFactory.getDriver().findElement(locator));
            }
        }
        catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", locator));
        }

    }

    public static void selectElementFromDropDownByText(By locator,String value)
    {

        WebElement select = MainCall.webDriverFactory.getDriver().findElement(locator);
        Select option = new Select(select);
        option.selectByVisibleText(value);
    }

    public static void clickSpecificElementFromList(By locator,int index)
    {
        List<WebElement> launchModule = MainCall.webDriverFactory.getDriver().findElements(locator);
        launchModule.get(index).click();
    }

    public static void slideBar(By locator1,By locator2, int xOffset,int yOffset)
    {
        WebElement slider = MainCall.webDriverFactory.getDriver().findElement(locator1).findElement(locator2);
        Actions act = new Actions(WebDriverFactory.getDriver());
        act.dragAndDropBy(slider,xOffset,yOffset).build().perform();
    }

    public static void inputTextAlert(String input)
    {
        MainCall.webDriverFactory.getDriver().switchTo().alert().sendKeys(input);
    }

    public static void acceptAlert()
    {
        MainCall.webDriverFactory.getDriver().switchTo().alert().accept();
    }
    public static void radioButtonClick(By locator,int index)
    {
        List<WebElement> radiobutton = WebDriverFactory.getDriver().findElements(locator);
        radiobutton.get(index).click();
    }

    public static void assertionToDisplayed(By locator)
    {
        try {
            MainCall.webDriverWaits.visibilityOf(locator);
            {
                org.junit.Assert.assertTrue(MainCall.webDriverFactory.getDriver().findElement(locator).isDisplayed());
            }
        }
        catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", locator));
        }
    }



    public static void click(By locator)
    {
        try {
             MainCall.webDriverWaits.visibilityOf(locator);
            {
                WebElement button = MainCall.webDriverWaits.waitUntilElementIsClickable(locator);
                {
                    button.click();
                }
            }
        }
        catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", locator));
        }
    }


}