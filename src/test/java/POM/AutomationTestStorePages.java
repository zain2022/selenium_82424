package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AutomationTestStorePages {

    WebDriver driver;
    public AutomationTestStorePages (WebDriver driver){
        this.driver = driver;
    }

    // Locators:
    By btn_login_or_register_locator = By.xpath("//a[normalize-space()='Login or register']");
    By txt_login_name_locator = By.xpath("//input[@id='loginFrm_loginname']");
    By txt_login_password_locator = By.xpath("//input[@id='loginFrm_password']");
    By btn_login_locator = By.xpath("//button[normalize-space()='Login']");
    By btn_automation_test_store_locator = By.xpath("//img[@title='Automation Test Store']");
    By brand_scrolling_list_locator = By.cssSelector("#block_frame_listing_block_1774");
    By dove_brand_locator = By.xpath("//img[@alt='Dove']");
    By sort_dropdown_locator = By.xpath("//select[@id='sort']");
    By product_list_locator = By.cssSelector(".thumbnails .col-md-3");
    By product_name_locator = By.cssSelector(".prdocutname");
    By product_price_locator = By.cssSelector(".oneprice");
    By add_to_cart_locator = By.cssSelector("a[title='Add to Cart']");
    By out_of_stock_locator = By.cssSelector(".nostock");
//    By product_quantity_locator = By.xpath("//span[@class='label label-orange font14'][normalize-space()='1']"); // class="label label-orange font14"
    By product_quantity_locator = By.xpath("//span[contains(@class, 'label label-orange font14')]");
    By view_cart_locator = By.xpath("ul[class='nav topcart pull-left'] a[class='dropdown-toggle']");

    public void loginAutomationStore(){
        driver.findElement(btn_login_or_register_locator).click();
        clearField(txt_login_name_locator);
        driver.findElement(txt_login_name_locator).sendKeys("zain2024");
        clearField(txt_login_password_locator);
        driver.findElement(txt_login_password_locator).sendKeys("zain2024");
        driver.findElement(btn_login_locator).click();
        driver.findElement(btn_automation_test_store_locator).click();

    }

    // Method to scroll to the Brands Scrolling List section
    int availableProductCount = 0;
    List<String> productNames = new ArrayList<>();
    List<String> productPrices = new ArrayList<>();
    List<String> productQuantities = new ArrayList<>();
    public void scrollToBrandList() {
        WebElement brandListSection = driver.findElement(brand_scrolling_list_locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(brandListSection).perform();
    }

    // Method to click on Dove brand
    public void clickOnDoveBrand() {
        WebElement doveBrand = driver.findElement(dove_brand_locator);
        doveBrand.click();
    }

    // Method to select sort by "Date New > Old"
    public void sortByDateNewToOld() {
        WebElement sortDropdown = driver.findElement(sort_dropdown_locator);
        Select select = new Select(sortDropdown);
//        select.selectByVisibleText("Date New > Old");
        select.selectByValue("date_modified-DESC");  // Select by value or you can also use visible text
    }

    // Method to find all products and add the first one that is available to cart

    // Method to add the first available product to the cart and save its details
    public void addFirstAvailableProductToCart() {
        List<WebElement> products = driver.findElements(product_list_locator);
        int availableProductCount = 0;

        for (WebElement product : products) {
            if (product.findElements(out_of_stock_locator).size() == 0) {  // Check if 'Out of Stock' is not present
                availableProductCount++;

                String productName = product.findElement(product_name_locator).getText();
                String productPrice = product.findElement(product_price_locator).getText();

                // Save product name and price
                productNames.add(productName);
                productPrices.add(productPrice);

                if (availableProductCount == 1) {  // Add first available product to cart
                    product.findElement(add_to_cart_locator).click();
                    System.out.println("Added to Cart: " + productName + " - " + productPrice);

                    // Get and save the quantity after adding to cart
                    WebElement quantityElement = driver.findElement(product_quantity_locator);
//                    productQuantities.add(quantityElement.getText().replace("$", ""));
                    System.out.printf(quantityElement.getText());
                    productQuantities.add(quantityElement.getText());
                    break;
                }
            }
        }

        if (availableProductCount < 1) {
            System.out.println("No available products found.");
        }
    }

    // Method to add the second available product to the cart and save its details
    public void addSecondAvailableProductToCart() {
        List<WebElement> products = driver.findElements(product_list_locator);
        int availableProductCount = 0;

        for (WebElement product : products) {
            if (product.findElements(out_of_stock_locator).size() == 0) {  // Check if 'Out of Stock' is not present
                availableProductCount++;

                String productName = product.findElement(product_name_locator).getText();
                String productPrice = product.findElement(product_price_locator).getText();

                // Save product name and price
                productNames.add(productName);
                productPrices.add(productPrice);

                if (availableProductCount == 2) {  // Add second available product to cart
                    product.findElement(add_to_cart_locator).click();
                    System.out.println("Added to Cart: " + productName + " - " + productPrice);

                    // Get and save the quantity after adding to cart
                    WebElement quantityElement = driver.findElement(product_quantity_locator);
                    productQuantities.add(quantityElement.getText());
                    break;
                }
            }
        }

        if (availableProductCount < 2) {
            System.out.println("Less than 2 available products found.");
        }
    }

    // Method to get the name, price, and quantity of the first and second added products for assertions
    public List<String> getAddedProductDetails() {
        List<String> productDetails = new ArrayList<>();

        // Add first added product details
        String firstProductName = productNames.get(0);  // Get name of first added product
        String firstProductPrice = productPrices.get(0);  // Get price of first added product
        String firstProductQuantity = productQuantities.get(0);  // Get quantity of first added product

        // Add to list
        productDetails.add(firstProductName);
        productDetails.add(firstProductPrice);
        productDetails.add(firstProductQuantity);

        // Print the product details
        System.out.println("First Product Details:");
        System.out.println("Name: " + firstProductName);
        System.out.println("Price: " + firstProductPrice);
        System.out.println("Quantity: " + firstProductQuantity);

        // Print details of the second product, uncomment below
//    String secondProductName = productNames.get(1);  // Get name of second added product
//    String secondProductPrice = productPrices.get(1);  // Get price of second added product
//    String secondProductQuantity = productQuantities.get(1);  // Get quantity of second added product

//    productDetails.add(secondProductName);
//    productDetails.add(secondProductPrice);
//    productDetails.add(secondProductQuantity);

//    System.out.println("Second Product Details:");
//    System.out.println("Name: " + secondProductName);
//    System.out.println("Price: " + secondProductPrice);
//    System.out.println("Quantity: " + secondProductQuantity);

        return productDetails;
    }

    public void viewCart(){
        WebElement viewCart = driver.findElement(view_cart_locator);
        viewCart.click();
    }

    public void clearField(By locator) {
        driver.findElement(locator).clear();
    }

}
