package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestCase {

    public static void main(String[] args){
        // Launch browser Chrome
        //ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();

        // Open URL https://www.opencart.com
        driver.get("https://www.opencart.com");
        driver.manage().window().maximize();

        // Validate title should be "OpenCart - Open Source Shopping Cart Solution"
        String actualTitle = driver.getTitle();
        if(actualTitle.equals("OpenCart - Open Source Shopping Cart Solution")){
            System.out.println("Test Passed");
        }
        else{
            System.out.println("Test Failed");
        }
        // Close browser
        //driver.quit();
        driver.close();
    }

}
