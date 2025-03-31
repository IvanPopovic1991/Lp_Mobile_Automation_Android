package TestsFortradeR;

import ConfigureAppium.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class test_Page extends BaseTest {

    @BeforeMethod
    public void setup() throws MalformedURLException, URISyntaxException {
        configureAppium();
    }

    @Test
    public void visitPage() {
        androidDriver.get("https://www.fortrader.com/minilps/en/pro-dark-2024-dlp");
    }

    @AfterMethod
    public void tearDown() {
        stopAppium();
    }
}
