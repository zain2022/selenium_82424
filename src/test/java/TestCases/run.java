//package TestCases;
//
//import POM.LoginPage;
//import POM.ProductDashboardPage;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.List;
//
//public class run {
//    WebDriver driver;
//    LoginPage login;
//    ProductDashboardPage productDashboardPage;
//
//    @BeforeMethod
//    public void setup() {
//        // Setup code to initialize WebDriver and navigate to the dashboard
//        driver = new ChromeDriver();
//        driver.get("https://www.saucedemo.com/"); // Replace with the actual URL
//
//        productDashboardPage = new ProductDashboardPage(driver);
//    }
//
//    @Test
//    public void testPriceSortingAndCart() {
//
//        // Step 1: Select the "low to high" filter
//        productDashboardPage.selectPriceLowToHigh();
//
//        // Step 2: Verify that prices are sorted low to high
//        List<Double> prices = productDashboardPage.getProductPrices();
//        Assert.assertTrue(productDashboardPage.isPriceSortedLowToHigh(prices), "Prices are not sorted from low to high!");
//
//        // Step 3: Add the lowest two items to the cart
//        productDashboardPage.addLowestTwoItemsToCart();
//
//        // Step 4: Verify the number of items in the cart
//        int cartItemCount = productDashboardPage.getCartItemCount();
//        Assert.assertEquals(cartItemCount, 2, "Cart does not contain 2 items!");
//
//        // Step 5: Verify the total price in the cart matches the sum of the lowest two prices
//        double expectedTotal = prices.get(0) + prices.get(1);
//        double actualTotal = productDashboardPage.getCartTotalPrice();
//        Assert.assertEquals(actualTotal, expectedTotal, "Total price in cart does not match the expected total of the lowest two items.");
//    }
//}
