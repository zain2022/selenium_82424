package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CSSLocator {
    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.nopcommerce.com/");

        //CAPTCHA
        // Check for CAPTCHA presence
        if (isCaptchaPresent(driver)) {
            System.out.println("CAPTCHA detected. Please solve it manually.");
            // Pause for manual CAPTCHA solving
            try {
                TimeUnit.MINUTES.sleep(2); // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        driver.findElement(By.cssSelector("input[type='checkbox']")).click();

        driver.manage().window().maximize();

        // CSS Selector

        // tag#id -> tag is optional
        driver.findElement(By.cssSelector("input#small-searchterms")).sendKeys("T-shirts");

        // tag.class
        driver.findElement(By.cssSelector("input.search-box-text")).sendKeys("T-shirts");

        // tga[attribute="value"]
        driver.findElement(By.cssSelector("input[placeholder='Search store']")).sendKeys("T-shirts");
        driver.findElement(By.cssSelector("input[placeholder=\"Search store\"]")).sendKeys("T-shirts"); // backward slash should be used for using double quotes in double quotes otherwise you can use single quotes.

        // tag.class[attribute="value"]
        driver.findElement(By.cssSelector("input.search-box-text[name='q']")).sendKeys("T-shirts"); // backward slash should be used for using double quotes in double quotes otherwise you can use single quotes.



    }
    public static boolean isCaptchaPresent(WebDriver driver) {
        // Modify the XPath according to the CAPTCHA element's XPath
        By captchaLocator = By.xpath("//div[contains(text(),'Please verify you are a human')]");
        WebElement captchaElement = driver.findElement(captchaLocator);
        return captchaElement != null && captchaElement.isDisplayed();
    }
}
