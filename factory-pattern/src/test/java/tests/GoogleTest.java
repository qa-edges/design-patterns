package tests;

import base.BaseTest;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class GoogleTest extends BaseTest {

    @Test
    public void launchGoogleTest() {
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test(dependsOnMethods = "launchGoogleTest")
    public void enterInSearchBox() {
        driver.findElement(By.name("q")).click();
        Uninterruptibles.sleepUninterruptibly(1,SECONDS);
        driver.findElement(By.name("q")).sendKeys("Selenium");
    }



}
