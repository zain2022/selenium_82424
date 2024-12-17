package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver){

        this.driver = driver;

    }

    // Locators
    By txt_username_locator = By.cssSelector("#user-name");
    By txt_password_locator = By.cssSelector("#password");
    By btn_login_locator = By.cssSelector("#login-button");
    By btn_hamburger_menu_locator = By.cssSelector("#react-burger-menu-btn");
    By btn_logout_locator = By.cssSelector("#logout_sidebar_link");

    public void setUserName(String name){

        clearField(txt_username_locator);
        driver.findElement(txt_username_locator).sendKeys(name);

    }

    public void setPassword(String password){

        clearField(txt_password_locator);
        driver.findElement(txt_password_locator).sendKeys(password);

    }

    public void clearField(By locator) {
        driver.findElement(locator).clear();
    }

    public void clickLogin(){

        driver.findElement(btn_login_locator).click();

    }

    public void clickHamburgerMenu(){

        driver.findElement(btn_hamburger_menu_locator).click();

    }

    public void clickLogout(){

        driver.findElement(btn_logout_locator).click();

    }

    public void assertErrorMessage(){

    }

}
