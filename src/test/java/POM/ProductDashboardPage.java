// ProductDashboardPage.java
package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDashboardPage {
    private WebDriver driver;

    // Constructor
    public ProductDashboardPage(WebDriver driver) {
        this.driver = driver;
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
        WebElement dropdown = driver.findElement(filterDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (low to high)");
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
        driver.findElement(cartIcon).click();
        driver.findElement(checkoutButton).click();
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
        driver.findElement(continueButton).click();
    }

    public int getCartQuantity() {
        return driver.findElements(cartQuantity).size();
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
        String subtotalText = driver.findElement(subtotal).getText().replace("Item total: $", "");
        return Double.parseDouble(subtotalText);
    }

    public void clickFinish(){
        driver.findElement(finish).click();
    }
}