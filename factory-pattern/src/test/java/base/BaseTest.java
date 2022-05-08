package base;

import driver_factory.DriverManager;
import driver_factory.DriverManagerFactory;
import driver_factory.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class BaseTest {

    DriverManager driverManager;
    protected WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void beforeTest(@Optional("Chrome") String browser) {
        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browser.toUpperCase()));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
    }

    @AfterTest
    public void afterTest() {
        driverManager.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        driverManager.stopService();
    }

}
