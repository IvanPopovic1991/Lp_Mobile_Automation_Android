package ConfigureAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;


public class BaseTest {
    protected AndroidDriver androidDriver;
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
        androidDriver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);
    }
        // Stop Appium service and driver quit
    public void stopAppium() {
       androidDriver.quit();
       service.stop();
    }
}
