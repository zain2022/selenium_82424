package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utilities.CommonFunction;

public class ProductDashboardPage {
    private WebDriver driver;
    private CommonFunction commonFunctions;

    // Constructor
    public ProductDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.commonFunctions = new CommonFunction(driver); // Initialize common functions
    }

    // Locators
    private By filterDropdown = By.cssSelector(".product_sort_container");
    private By priceList = By.cssSelector("div[class='inventory_list'] div:nth-child(1) div:nth-child(2) div:nth-child(2) div:nth-child(1)");
    private By addToCartButtons = By.cssSelector("button[data-test^='add-to-cart']");
    private By cartIcon = By.xpath("//a[@class='shopping_cart_link']");
    private By checkoutButton = By.xpath("//button[@id='checkout']");
    private By firstNameField = By.cssSelector("#first-name");
    private By lastNameField = By.cssSelector("#last-name");
    private By postalCodeField = By.cssSelector("#postal-code");
    private By continueButton = By.cssSelector("#continue");
    private By cartQuantity = By.cssSelector("div.cart_quantity");
    private By cartPrices = By.cssSelector("div.inventory_item_price");
    private By subtotal = By.cssSelector("div.summary_subtotal_label");
    private By finish = By.cssSelector("#finish");

    // Actions
    public void openFilterDropdownAndSelectPriceLowToHigh() {
        commonFunctions.selectDropdownByVisibleText(filterDropdown, "Price (low to high)");
    }

    public List<Double> getItemPrices() {
        List<WebElement> priceElements = driver.findElements(priceList);
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public boolean isPriceSortedLowToHigh(List<Double> prices) {
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        return prices.equals(sortedPrices);
    }

    public void addLowestPricedItemsToCart(int itemCount) {
        List<WebElement> addToCartElements = driver.findElements(addToCartButtons);
        for (int i = 0; i < itemCount; i++) {
            addToCartElements.get(i).click();
        }
    }

    public void goToCartAndCheckout(String firstName, String lastName, String postalCode) {
        commonFunctions.clickElement(cartIcon);
        commonFunctions.clickElement(checkoutButton);
        commonFunctions.sendKeysToField(firstNameField, firstName);
        commonFunctions.sendKeysToField(lastNameField, lastName);
        commonFunctions.sendKeysToField(postalCodeField, postalCode);
        commonFunctions.clickElement(continueButton);
    }

    public int getCartQuantity() {
        return commonFunctions.getElementListSize(cartQuantity);
    }

    public double getCartTotal() {
        List<WebElement> priceElements = driver.findElements(cartPrices);
        double total = 0;
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            total += Double.parseDouble(priceText);
        }
        return total;
    }

    public double getSubtotal() {
        String subtotalText = commonFunctions.getElementText(subtotal).replace("Item total: $", "");
        return Double.parseDouble(subtotalText);
    }

    public void clickFinish(){
        commonFunctions.clickElement(finish);
    }
}