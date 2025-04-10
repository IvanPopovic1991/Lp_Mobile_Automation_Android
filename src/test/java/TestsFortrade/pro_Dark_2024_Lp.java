package TestsFortrade;

import ConfigureAppium.BaseTest;
import Faker.TestData;
import Pages.FortradePage;
import Pages.HomePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class pro_Dark_2024_Lp extends BaseTest {

    FortradePage fortradePage;

    @Parameters({"tag"})
    @BeforeMethod
    public void setup(String tag) throws MalformedURLException, URISyntaxException {
        configureAppium();
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/" + tag + "&fts=age-annual-saving-knowledge");
        waitForElement(driver.findElement(By.xpath("//input[@id='PhoneCountryCode']")), "0");
        fortradePage = new FortradePage((AndroidDriver) driver);
    }

    @Parameters({"countryCode", "regulation"})
    @Test
    public void demoAccountRegistration(String countryCode, String regulation) throws IOException, AWTException {
        fortradePage.accountRegistration("Testq", "Testa", TestData.emailGenerator(),
                countryCode, TestData.numberGenerator(), "25-34", "$50,000-$100,000",
                "$50,000 – $100,000", "All the above");
        HomePage homePage = new HomePage((AndroidDriver) driver);
        homePage.clickDenyBtn();
        homePage.clickUsePassBtn();
        if (regulation.equals("iiroc")) {
            homePage.closePersonalizeYourContent();
        } else if (regulation.equals("Asic")) {
            homePage.clickConsentBtn();
        }
        homePage.clickMenuBtn();
        homePage.checkRegulation(regulation);
    }

    @AfterMethod
    public void tearDown() {
        stopAppium();
    }
}
