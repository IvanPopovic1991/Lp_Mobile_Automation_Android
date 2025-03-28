package Selenium_Core;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.concurrent.TimeUnit;

public class OperaDriverManager extends DriverManager{
    @Override
    public void createWebDriver(String version) {
        String projectPath = System.getProperty("user.dir");
    System.setProperty("opera.webdriver.driver",projectPath+"src/main/resources/operadriver.exe");
        OperaOptions options = new OperaOptions();
        driver = new OperaDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
