package TestsFortradeR;

import Selenium_Core.DriverManager;
import Selenium_Core.DriverManagerFactory;
import org.openqa.selenium.WebDriver;

public class BaseFortradeR {

    DriverManager driverManager;
    WebDriver driver;

    public void baseSetup(String browser, String version){
       driverManager = DriverManagerFactory.getDriverManager(browser);
       driver = driverManager.getWebDriver(version);
    }

    public void baseTearDown(){
        driver.quit();
    }
}
