package TestsFortradeR;

import ConfigureAppium.BaseTest;
import Faker.TestData;
import Pages.FortradeRPage;
import Pages.HomePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class pro_Dark_2024_Lp extends BaseTest {

    FortradeRPage fortraderPage;

    @BeforeMethod
    public void setup() throws MalformedURLException, URISyntaxException {
        configureAppium();
        driver.get("https://www.fortrader.com/minilps/en/pro-dark-2024-dlp/?fts=age-annual-saving-knowledge");
        waitForElement(driver.findElement(By.xpath("//input[@id='PhoneCountryCode']")),"0");
        fortraderPage = new FortradeRPage((AndroidDriver) driver);
    }
    @Test
    public void demoAccountRegistration() throws IOException, AWTException {
        fortraderPage.accountRegistration("Testq", "Testa", TestData.emailGenerator(),
                "381", TestData.numberGenerator(), "25-34", "$50,000-$100,000",
                "$50,000 – $100,000", "All the above");
        HomePage homePage = new HomePage((AndroidDriver) driver);
        homePage.clickDenyBtn();
        homePage.clickNotResBtn();
        homePage.clickUsePassBtn();
        homePage.clickMenuBtn();
        homePage.checkRegulation("FSC");
    }

    @AfterMethod
    public void tearDown() {
        stopAppium();
    }
}
