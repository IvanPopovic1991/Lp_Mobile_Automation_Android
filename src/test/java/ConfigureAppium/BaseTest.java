package ConfigureAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.io.FileOutputStream;
import java.util.Base64;

public class BaseTest {

    protected AppiumDriver driver;
    protected AppiumDriverLocalService service;

    public void configureAppium() throws URISyntaxException, MalformedURLException {
        // Start Appium server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Ivan Popovic\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        // Set up capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("platformName", "Android");
        options.setCapability("deviceName", "inpb95xslj7p9lpb");
        options.setCapability("automationName", "UiAutomator2");
        options.setCapability("noReset", true);
        options.setCapability("appium:browserName", "chrome");
        options.setCapability("chromedriverExecutable", "C:\\Users\\Ivan Popovic\\Desktop\\Documentation\\Lp_Mobile_Automation_Android\\src\\main\\resources\\chromedriver134.exe");
        options.setCapability("chromedriver_autodownload", true);

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

    public void stopAppium() {
        driver.quit();
        service.stop();
    }

}
