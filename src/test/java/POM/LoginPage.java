package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.CommonFunction;

public class LoginPage {
    WebDriver driver;
    CommonFunction commonFunctions;

    // Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.commonFunctions = new CommonFunction(driver); // Initialize common functions
    }

    // Locators
    By txt_username_locator = By.cssSelector("#user-name");
    By txt_password_locator = By.cssSelector("#password");
    By btn_login_locator = By.cssSelector("#login-button");
    By btn_hamburger_menu_locator = By.cssSelector("#react-burger-menu-btn");
    By btn_logout_locator = By.cssSelector("#logout_sidebar_link");

    public void setUserName(String name){
        commonFunctions.clearField(txt_username_locator);
        commonFunctions.sendKeysToField(txt_username_locator, name);
    }

    public void setPassword(String password){
        commonFunctions.clearField(txt_password_locator);
        commonFunctions.sendKeysToField(txt_password_locator, password);
    }

    public void clickLogin(){
        commonFunctions.clickElement(btn_login_locator);
    }

    public void clickHamburgerMenu(){
        commonFunctions.clickElement(btn_hamburger_menu_locator);
    }

    public void clickLogout(){
        commonFunctions.clickElement(btn_logout_locator);
    }

    public void assertErrorMessage(){
        // Placeholder for error message assertion if needed
    }
}
