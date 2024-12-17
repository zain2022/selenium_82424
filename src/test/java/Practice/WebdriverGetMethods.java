package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Set;

public class WebdriverGetMethods {

    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();

        // opens the URL on the nrowser
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        // returns the title of th epage
        System.out.println(driver.getTitle());

        // returns URL of the page
        System.out.println(driver.getCurrentUrl());

        // returns source cod eof the page
        System.out.println(driver.getPageSource());

        // returns ID of the single browser window
        String windowId = driver.getWindowHandle();
        System.out.println("Window ID: " + windowId);

        driver.findElement(By.linkText("OrangeHRM, Inc")).click();
//        driver.findElement(By.xpath("//a[normalize-space()='OrangeHRM, Inc']")).click();
//        driver.findElement(By.cssSelector("a[href='http://www.orangehrm.com']")).click();

        // returns ID's of the multiple browser windows
        Set<String> windowIds = driver.getWindowHandles();
        System.out.println(windowIds);

    }

}
