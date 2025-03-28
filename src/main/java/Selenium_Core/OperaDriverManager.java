package Selenium_Core;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.concurrent.TimeUnit;

public class OperaDriverManager extends DriverManager {
    @Override
    public void createWebDriver(String version) {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.opera.driver", projectPath + "/src/main/resources/operadriver" + version + ".exe");
        String binaryFilePath = System.getenv("OperaExeFilePath");
        OperaOptions options = new OperaOptions();
        options.setBinary(binaryFilePath);
        driver = new OperaDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
