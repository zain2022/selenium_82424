package TestCases;

import POM.AutomationTestStorePages;
import org.testng.annotations.Test;
import utilities.BaseTest;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class AutomationStoreTestCases extends BaseTest {

    AutomationTestStorePages AutomationStore;

    @BeforeClass
    void initializePageObjects() {
        navigateTo("https://automationteststore.com/");
        AutomationStore = new AutomationTestStorePages(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(priority = 1)
    void automationTestStore_1(){
        AutomationStore.loginAutomationStore();
        AutomationStore.scrollToBrandList();
        AutomationStore.clickOnDoveBrand();
        AutomationStore.sortByDateNewToOld();
        AutomationStore.addFirstAvailableProductToCart();
        AutomationStore.getAddedProductDetails();

    }

}
