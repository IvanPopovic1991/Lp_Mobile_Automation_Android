package ConfigureAppium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.io.FileOutputStream;
import java.util.Base64;

public class BaseTest {

    protected AppiumDriver driver;
    protected ChromeDriver chromeDriver;
    protected AppiumDriverLocalService service;

    String chromeDriverFile = System.getenv("ChromeDriverExeFilePath");

    public void configureAppium() throws URISyntaxException, MalformedURLException {
        stopWebBrowser();
        /*// Start Appium server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C://Users//newUser//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();*/

        // Set up capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("platformName", "Android");
        options.setCapability("deviceName", "RFCNC0B9N0W");
        options.setCapability("automationName", "UiAutomator2");
        options.setCapability("noReset", true);
        options.setCapability("appium:browserName", "chrome");
        options.setCapability("chromedriverExecutable", chromeDriverFile);
        options.setCapability("chromedriver_autodownload", true);


        /*//Appium code -> Appium server -> Mobile
        UiAutomator2Options options = new UiAutomator2Options();
        //options.setDeviceName("Testing mobile");
        //options.setUdid("emulator-5554");
        options.setDeviceName("Android phone");
        options.setUdid("RFCNC0B9N0W");
        options.setCapability("enforceXPath1", true);

        options.setCapability("allowInsecure", Arrays.asList("chromedriver_autodownload"));
        options.setCapability("chromedriver_autodownload", true);
        options.setCapability("ignoreHiddenApiPolicyError", true);
        options.setCapability("disableWindowAnimation", true);
        options.setCapability("skipDeviceInitialization", true);

        options.setCapability("chromeOptions", ImmutableMap.of("w3c", false, "args", Arrays.asList("no-first-run", "disable-popup-blocking")));
        options.setCapability("noReset", true);  // Do not clear app data
        options.setCapability("fullReset", false); // Prevent full reset

        options.setCapability("dontStopAppOnReset", true);

        //This is set up for chrome browser
        options.setChromedriverExecutable("C:\\Users\\newUser\\Desktop\\Drivers135\\chromedriver-win64\\chromedriver.exe");
        *//*options.setChromedriverExecutable("C:\\Users\\newUser\\Desktop\\Drivers135\\chromedriver-win64\\chromedriver.exe");*//*
        options.setCapability("browserName", "Chrome");*/




        // Start AndroidDriver
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public void waitForElement(WebElement element, String value) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                // .until(ExpectedConditions.visibilityOf(element));
                .until(ExpectedConditions.textToBePresentInElementValue(element, value));
    }

    public static void saveVideo(String base64Video, String fileName) {
        try {
            byte[] data = Base64.getDecoder().decode(base64Video);
            File dir = new File("src/test-recordings");
            if (!dir.exists()) dir.mkdirs();
            File videoFile = new File(dir, fileName + ".mp4");
            try (FileOutputStream fos = new FileOutputStream(videoFile)) {
                fos.write(data);
            }
            System.out.println("Saved video: " + videoFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Failed to save video: " + e.getMessage());
        }
    }

    public void startChromeBrowserOnDesktop(){
        stopAppium();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver135.exe");
        String filePath = System.getenv("ChromeExeFilePath");
        ChromeOptions options = new ChromeOptions();
        options.setBinary(filePath);
        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void stopAppium() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }/*
        service.stop();*/
    }

    public void stopWebBrowser(){
        if (chromeDriver != null) {
            chromeDriver.quit();
            chromeDriver = null;
        }
    }

}
