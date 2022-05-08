package driver_factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverManager extends DriverManager {

    private GeckoDriverService ffService;

    @Override
    public void startService() {
        if (null == ffService) {
            try {
                WebDriverManager.firefoxdriver().setup();
                new GeckoDriverService.Builder()
                        //.usingDriverExecutable(new File("geckodriver.exe"))
                        .usingAnyFreePort()
                        .build();
                ffService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != ffService && ffService.isRunning())
            ffService.stop();
    }

    @Override
    public void createDriver() {

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.no_proxies_on", "localhost");
        profile.setPreference("javascript.enabled", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("Firefox");
        capabilities.setCapability("marionette", true);

        FirefoxOptions options = new FirefoxOptions();
        options.merge(capabilities);
        options.setLogLevel(FirefoxDriverLogLevel.DEBUG);
        options.addArguments("test-type");
        options.addPreference("browser.link.open_newwindow", 3);
        options.addPreference("browser.link.open_newwindow.restriction", 0);
        driver = new FirefoxDriver(options);

        options.merge(capabilities);
        driver = new FirefoxDriver(ffService, options);
    }

}
