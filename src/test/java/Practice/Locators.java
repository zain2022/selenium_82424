package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Locators {

    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.opencart.com/");
        driver.manage().window().maximize();

//        // name
//        driver.findElement(By.name("search")).sendKeys("Mac");
//
//        // id
//        boolean logoDisplaysdStatus = driver.findElement(By.id("logo")).isDisplayed();
//        System.out.println(logoDisplaysdStatus);

        // linkText and partialLinkText
        // driver.findElement(By.linkText("Tablets")).click();
        //driver.findElement(By.partialLinkText("Table")).click(); // Not recommended

        // tagName and class is use to locate multiple or group of web elements.

        // how many links are there in the header?
        // className
        // List<WebElement> headerLinks = driver.findElements(By.className("list-inline-item"));
        // System.out.println("Total no of header links: "+headerLinks.size());

        // how many links are there in this complete page?
        // tagName
        //List<WebElement> links = driver.findElements(By.tagName("a"));
        //System.out.println("Total no of links: "+links.size());

        // how many total images are there in this page?
//        List<WebElement> images = driver.findElements(By.tagName("img"));
//        System.out.println("When using findElement and locator found multiple elements. Total no of images: "+images.size());
//
//        List<WebElement> images1 = driver.findElements(By.tagName("imgabc"));
//        System.out.println("When using findElements and locator not found any of the element, will return 0  : "+images1.size());
//
//        WebElement images2 = driver.findElement(By.tagName("img"));
//        System.out.println("When using findElement and locator found multiple elements, will Return the first element: "+images2.getAttribute("alt"));
//
//        WebElement images3 = driver.findElement(By.tagName("imgvfdc"));
//        System.out.println("When using findElement and locator not found any of the element, will return exception Unable to locate element: "+images3);

        // CSS Selector



        driver.close();

    }

}
