package Selenium_Core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager {

    @Override
    public void createWebDriver(String version) {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/resources/chromedriver"+version+".exe");
        String filePath = System.getenv("ChromeExeFilePath");
        ChromeOptions options = new ChromeOptions();
        options.setBinary(filePath);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
