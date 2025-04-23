package TestsFortrade;

import ConfigureAppium.BaseTest;
import Faker.TestData;
import Pages.BasePage;
import Pages.FortradePage;
import Pages.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.By;
import org.testng.Assert;
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
    protected void setup(String tag) throws MalformedURLException, URISyntaxException {
        configureAppium();
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/" + tag + "&fts=age-annual-saving-knowledge");
        waitForElement(driver.findElement(By.xpath("//input[@id='PhoneCountryCode']")), "0");
        fortradePage = new FortradePage((AndroidDriver) driver);
    }

    @Parameters({"countryCode", "regulation"})
    @Test
    protected void demoAccountRegistration(String countryCode, String regulation) throws IOException, AWTException {

        //Start recording
        ((CanRecordScreen) driver).startRecordingScreen();
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

        // stop recording
        String video = ((CanRecordScreen) driver).stopRecordingScreen();
        BaseTest.saveVideo(video, "Demo account registration " + regulation + " regulation");
    }

    @Parameters({"regulation"})
    @Test
    protected void unsuccessfullyDemoAccountRegistration(String regulation) throws IOException {
        fortradePage.unsuccessfullyRegistrationWrongData("123", "/*-", "*+-", "asd",
                "jklQyp");
        fortradePage.assertErrorMessages();
        fortradePage.assertColor("Red");
        fortradePage.takeScreenshot("Unsuccessfully demo account registration - " + regulation + " - regulation");
    }

    @Parameters({"tag", "countryCode", "regulation"})
    @Test
    protected void assertInvalidTokenMsg(String tag, String countryCode, String regulation) throws IOException {
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/" + tag + "&fts=sms-age-annual-saving-knowledge");
        fortradePage.unsuccessfullyRegistrationWrongSMS("Testq", "Testa", TestData.emailGenerator(), countryCode,
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above",
                "1", "1", "1", "1");
        Assert.assertEquals((fortradePage.incorrectTokenMsg.getText()), "Incorrect Code. Please check and try again.");
        fortradePage.takeScreenshot("Incorrect code - Please check and try again - " + regulation);
    }

    @Parameters({"regulation"})
    @Test
    protected void unsuccessfullyDemoAccRegEmptyData(String regulation) throws IOException {
        fortradePage.unsuccessfullyRegistrationWrongData(" ", " ", " ", " ", " ");
        fortradePage.assertErrorMessages();
        fortradePage.assertColor("red");
        fortradePage.takeScreenshot("Unsuccessfully demo account registration-no data- " + regulation);
    }

    @Parameters({"countryCode", "tag", "regulation"})
    @Test
    protected void anAlreadyRegisteredEmail(String countryCode, String tag, String regulation) throws IOException {
        String email = TestData.emailGenerator();
        fortradePage.accountRegistration("Testq", "Testa", email, countryCode, TestData.numberGenerator(),
                "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above");
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/" + tag);
        fortradePage.firstStepWidget("Testq", "Testa", email, countryCode, TestData.numberGenerator());
        fortradePage.assertPopUpAlreadyRegisteredAccount();
        fortradePage.takeScreenshot("An already registered email address - " + regulation + " regulation");
    }

    @Parameters({"countryCode", "tag", "regulation"})
    @Test
    protected void anAlreadyRegisteredPhone(String countryCode, String tag, String regulation) throws IOException {
        String phone = TestData.numberGenerator();
        fortradePage.accountRegistration("Testq", "Testa", TestData.emailGenerator(), countryCode,
                phone, "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above");
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/" + tag);
        fortradePage.firstStepWidget("Testq", "Testa", TestData.emailGenerator(), countryCode, phone);
        fortradePage.assertPopUpAlreadyRegisteredAccount();
        fortradePage.takeScreenshot("An already registered phone - " + regulation + " regulation");
    }

    @Parameters({"countryCode", "tag", "regulation"})
    @Test
    protected void anAlreadyRegisteredEmailAndPhone(String countryCode, String tag, String regulation) throws IOException {
        String email = TestData.emailGenerator();
        String phone = TestData.numberGenerator();
        fortradePage.accountRegistration("Testq", "Testa", email, countryCode,
                phone, "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above");
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/" + tag);
        fortradePage.firstStepWidget("Testq", "Testa",email, countryCode, phone);
        fortradePage.assertPopUpAlreadyRegisteredAccount();
        fortradePage.takeScreenshot("An already registered email and phone - " + regulation + " regulation");
    }

    @Parameters({"countryCode","regulation"})
    @Test
    protected void sameFNameAndLName(String countryCode,String regulation) throws IOException {
        fortradePage.clickDenyBtn();
        fortradePage.firstStepWidget("Testq","Testq",TestData.emailGenerator(),countryCode,TestData.numberGenerator());
        fortradePage.assertSameNameErrorMsg();
        fortradePage.takeScreenshot("Error messages for the same first and last name - " + regulation + " regulation");
    }

    @AfterMethod
    protected void tearDown() {
        stopAppium();
    }

}
