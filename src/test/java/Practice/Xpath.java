package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Xpath {
    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        // xpath with single attribute
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");

        // xpath with multiple attributes
        driver.findElement(By.xpath("//input[@id='password'][@placeholder=\"Password\"]")).sendKeys("secret_sauce");

        String loginCredentials = driver.findElement(By.xpath("//div[@id='login_credentials']")).getText();
        System.out.println("Login Credentials: "+ loginCredentials);
        System.out.println("Login Credentials (standard_user) is present: "+ loginCredentials.contains("standard_user"));

        // xpath with "and" "or" operators
//        driver.findElement(By.xpath("//input[@id='password' and @placeholder=\"Password\"]")).sendKeys("secret_sauce");
//        driver.findElement(By.xpath("//input[@id='password' or @placeholder=\"password\"]")).sendKeys("secret_sauce");

        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        // xpath with text() - inner text (linkText and partialLinkText)
        // inner text should be a linkText means should be in anchor tag (<a></a>) then we can use text() method with xpath.
//        driver.findElement(By.xpath("//a/div[text()='Sauce Labs Onesie']")).click();

        // capturing the element using xpath and text() - inner text for a non anchor tag (<a></a>) element
//        boolean isDisplaysStatus = driver.findElement(By.xpath("//span[text()='Products']")).isDisplayed();
//        String value1 = driver.findElement(By.xpath("//span[text()='Products']")).getText();
//        System.out.println("Element displays status: "+isDisplaysStatus);
//        System.out.println("Element value: "+value1);

        // xpath with contains()
        String value2 = driver.findElement(By.xpath("//span[contains(@class, 'itl')]")).getText();
        System.out.println("Element value using contains method: "+value2);

        // xpath with starts-with()
        String value3 = driver.findElement(By.xpath("//span[starts-with(@class, 'tit')]")).getText();
        System.out.println("Element value using starts-with method: "+value3);

        // Handling Dynamic Attributes


//        driver.close();
    }
}
