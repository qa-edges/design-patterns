package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YahooTest extends BaseTest {

    @Test
    public void launchYahooTest() {
        driver.get("https://www.yahoo.com");
        System.out.println(driver.getTitle());
        Assert.assertEquals("Yahoo Search - Web Search", driver.getTitle());
    }

}
