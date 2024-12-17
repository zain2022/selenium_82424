package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AutomationTestStore {
    public static void main(String[] args) {
        // Initialize WebDriver and set properties
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Wait object with 10 seconds timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to website and wait until fully loaded
        driver.get("https://automationteststore.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Login or register']")));

        // Click Login/Register and wait for login page to load
        driver.findElement(By.xpath("//a[normalize-space()='Login or register']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='loginFrm_loginname']")));

        // Clear and input login credentials, then login
        WebElement loginField = driver.findElement(By.xpath("//input[@id='loginFrm_loginname']"));
        loginField.clear();
        loginField.sendKeys("zain2024");

        WebElement passwordField = driver.findElement(By.xpath("//input[@id='loginFrm_password']"));
        passwordField.clear();
        passwordField.sendKeys("zain2024");

        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        wait.until(ExpectedConditions.urlToBe("https://automationteststore.com/index.php?rt=account/account"));

        // Assertions for post-login page
        assert driver.getCurrentUrl().equals("https://automationteststore.com/index.php?rt=account/account");
        assert driver.findElement(By.xpath("//span[@class='maintext']")).getText().equals(" My Account");
        assert driver.findElement(By.xpath("(//span[@class='subtext'])[1]")).getText().equals("Zain");

        // Navigate back to main page and scroll to "Brands Scrolling List"
        driver.findElement(By.xpath("//img[@title='Automation Test Store']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Brands Scrolling List']")));

        // Scroll to brand carousel and select "Dove"
        List<WebElement> brands = driver.findElements(By.xpath("//ul[@id='brandcarousal']//li"));
        for (WebElement brand : brands) {
            if (brand.getText().contains("Dove")) {
                brand.click();
                break;
            }
        }
//        wait.until(ExpectedConditions.urlToBe("https://automationteststore.com/index.php?rt=product/manufacturer&manufacturer_id=18"));
        wait.until(ExpectedConditions.urlToBe("https://automationteststore.com/"));

        // Scroll to
        assert driver.getCurrentUrl().equals("https://automationteststore.com/index.php?rt=product/manufacturer&manufacturer_id=18");

        // Sort products by "Date New > Old"
        WebElement sortDropdown = driver.findElement(By.xpath("//select[@id='sort']"));
        sortDropdown.click();
        sortDropdown.findElement(By.xpath("//option[@value='date_modified-DESC']")).click();

        // Find the first available Dove item and add it to the cart
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='pricetag jumbotron']//a[@title='Add to Cart']"));
        WebElement firstProduct = products.get(0);
        String productPrice = driver.findElement(By.xpath("(//div[@class='oneprice'])[1]")).getText();
        firstProduct.click();

        // Go to cart and verify product details
        driver.findElement(By.xpath("//ul[@class='nav topcart pull-left']//a[@class='dropdown-toggle']")).click();
        wait.until(ExpectedConditions.urlToBe("https://automationteststore.com/index.php?rt=checkout/cart"));
        assert driver.getCurrentUrl().equals("https://automationteststore.com/index.php?rt=checkout/cart");

        // Validate cart details: Unit Price, Quantity, and Total Price
        String unitPrice = driver.findElement(By.xpath("//td[@class='align_right']")).getText();
        String quantity = driver.findElement(By.xpath("//input[@name='quantity[76]']")).getAttribute("value");
        String totalPrice = driver.findElement(By.xpath("(//td[@class='align_right'])[2]")).getText();

        assert unitPrice.equals(productPrice);
        assert Integer.parseInt(quantity) == 1;
        assert totalPrice.equals(unitPrice);

        // Close the browser
        driver.quit();
    }

//    public void scrollUntilElementIsVisible() {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        boolean isElementFound = false;
//
//        // Define the XPath of the element you want to find
//        By elementXPath = By.xpath("//div[@id='block_frame_listing_block_1774']//span[@class='maintext']");
//
//        // Scroll down until the element is found
//        while (!isElementFound) {
//            try {
//                // Try to find the element
//                WebElement element = driver.findElement(elementXPath);
//                if (element.isDisplayed()) {
//                    isElementFound = true;  // Element is found and visible
//                    System.out.println("Element found!");
//                }
//            } catch (NoSuchElementException e) {
//                // Scroll down by 500px
//                js.executeScript("window.scrollBy(0, 500);");
//
//                // You can add a small delay to avoid scrolling too fast
//                try {
//                    Thread.sleep(1000);  // 1 second delay
//                } catch (InterruptedException ie) {
//                    ie.printStackTrace();
//                }
//            }
//        }
//    }
}