package Selenium_Core;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class EdgeDriverManager extends DriverManager{
    @Override
    public void createWebDriver(String version) {
        String projectPath=System.getProperty("user.dir");
        System.setProperty("webdriver.edge.driver",projectPath+"src/main/resources/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
