package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
public class CommonFunction {
    private WebDriver driver;

    // Constructor
    public CommonFunction(WebDriver driver) {
        this.driver = driver;
    }

    // General function to click an element
    public void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    // General function to send text to a field
    public void sendKeysToField(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // General function to select an option from a dropdown
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = driver.findElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    // General function to clear a text field
    public void clearField(By locator) {
        WebElement element = driver.findElement(locator);
        element.clear();
    }

    // General function to get the text of an element
    public String getElementText(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }

    // General function to get the size of an element list
    public int getElementListSize(By locator) {
        return driver.findElements(locator).size();
    }
}
