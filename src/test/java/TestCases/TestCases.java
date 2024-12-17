package TestCases;

import POM.LoginPage;
import POM.ProductDashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.ConfigManager;

import java.time.Duration;
import java.util.List;

public class TestCases extends BaseTest {

    LoginPage login;
    ProductDashboardPage dashboardPage;
    String saucedemo_baseURL = ConfigManager.getProperty("saucedemo_baseURL");
    String username = ConfigManager.getProperty("username");
    String wrong_password = ConfigManager.getProperty("wrong_password");
    String password = ConfigManager.getProperty("password");



    @BeforeClass
    void initializePageObjects() {
        navigateTo(saucedemo_baseURL);
//        navigateTo("https://www.saucedemo.com/");
        login = new LoginPage(driver);
        dashboardPage = new ProductDashboardPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(priority = 1)
    void loginWithInvalidPassword() {
        login.setUserName(username);
        login.setPassword(wrong_password);
        login.clickLogin();

        // Assert: Error message is displayed
        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Epic sadface: Username and password do not match any user in this service");
    }

//    @Test(priority = 2, dependsOnMethods = "loginWithInvalidPassword")
    @Test(priority = 2)
    void loginWithValidPassword() {
        login.setUserName(username);
        login.setPassword(password);
        login.clickLogin();

        // Assert: User lands on the Product dashboard
        WebElement dashboardElement = driver.findElement(By.cssSelector(".title"));
        Assert.assertTrue(dashboardElement.isDisplayed(), "User did not land on the dashboard");
        Assert.assertEquals(dashboardElement.getText(), "Products", "User did not land on the Products page");

//        logout();
    }

    @Test(priority = 3)
    public void testAddLowestPricedItemsToCart() {
//        loginWithValidPassword();
        Assert.assertNotNull(dashboardPage, "ProductDashboardPage should be initialized");

        // Step 1: Go to dashboard and apply filter
        dashboardPage.openFilterDropdownAndSelectPriceLowToHigh();

        // Step 2: Verify items are sorted from low to high
        List<Double> prices = dashboardPage.getItemPrices();
        Assert.assertTrue(dashboardPage.isPriceSortedLowToHigh(prices), "Items are not sorted by price from low to high");

        // Step 3: Add the two lowest-priced items to the cart
        dashboardPage.addLowestPricedItemsToCart(2);

        // Step 4: Go to cart and checkout
        dashboardPage.goToCartAndCheckout("John", "Doe", "12345");

        // Assertions
        // Verify the quantity in the cart
        Assert.assertEquals(dashboardPage.getCartQuantity(), 2, "Cart does not contain 2 items");

        // Verify the total price in the cart
        double expectedTotal = dashboardPage.getCartTotal();
        double actualSubtotal = dashboardPage.getSubtotal();
        Assert.assertEquals(actualSubtotal, expectedTotal, "Subtotal does not match the total price of items in the cart");

        dashboardPage.clickFinish();
    }
}