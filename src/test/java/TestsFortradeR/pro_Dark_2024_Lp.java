package TestsFortradeR;

import ConfigureAppium.BaseTest;
import Faker.TestData;
import Pages.FortradeRPage;
import Pages.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.By;
import org.testng.Assert;
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
        waitForElement(driver.findElement(By.xpath("//input[@id='PhoneCountryCode']")), "0");
        fortraderPage = new FortradeRPage((AndroidDriver) driver);
    }

    @Test
    public void demoAccountRegistration() throws IOException, AWTException {

        //Start recording
        ((CanRecordScreen) driver).startRecordingScreen();
        fortraderPage.accountRegistration("Testq", "Testa", TestData.emailGenerator(),
                "381", TestData.numberGenerator(), "25-34", "$50,000-$100,000",
                "$50,000 – $100,000", "All the above");
        HomePage homePage = new HomePage((AndroidDriver) driver);
        homePage.clickDenyBtn();
        homePage.clickNotResBtn();
        homePage.clickUsePassBtn();
        homePage.clickMenuBtn();
        homePage.checkRegulation("FSC");
        //Stop recording
        String video = ((CanRecordScreen) driver).stopRecordingScreen();
        BaseTest.saveVideo(video, "Demo account registration - FortradeR");
    }

    @Test
    public void unsuccessfullyDemoAccountRegistration() throws IOException {
        fortraderPage.unsuccessfullyRegistrationWrongData("0000","9999","*+-","asd",
                "pjqyQ");
        fortraderPage.assertErrorMessages();
        fortraderPage.assertColor("Red");
        fortraderPage.takeScreenshot("Unsuccessfully demo account registration-FortradeR");
    }

    @Test
    public void assertInvalidTokenMsg() throws IOException {
        driver.get("https://www.fortrader.com/minilps/en/pro-dark-2024-dlp/?fts=sms-age-annual-saving-knowledge");
        fortraderPage.unsuccessfullyRegistrationWrongSMS("Testq", "Testa", TestData.emailGenerator(), "381",
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above",
                "1", "1", "1", "1");
        Assert.assertEquals((fortraderPage.incorrectTokenMsg.getText()), "Incorrect Code. Please check and try again.");
        fortraderPage.takeScreenshot("Incorrect code - Please check and try again - FortradeR");
    }

    @Test
    public void unsuccessfullyDemoAccRegEmptyData() throws IOException {
        fortraderPage.unsuccessfullyRegistrationWrongData("", "", "", "", "");
        fortraderPage.assertErrorMessages();
        fortraderPage.assertColor("red");
        fortraderPage.takeScreenshot("Unsuccessfully demo account registration - no data - FortradeR");
    }

    @Test
    protected void anAlreadyRegisteredEmail() throws IOException {
        String email = TestData.emailGenerator();
        fortraderPage.accountRegistration("Testq", "Testa", email, "381", TestData.numberGenerator(),
                "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above");
        driver.get("https://www.fortrader.com/minilps/en/pro-dark-2024-dlp/");
        fortraderPage.firstStepWidget("Testq", "Testa", email,"381", TestData.numberGenerator());
        fortraderPage.assertPopUpAlreadyRegisteredAccount();
        fortraderPage.takeScreenshot("An already registered email address - FortradeR");
    }

    @Test
    protected void anAlreadyRegisteredPhone() throws IOException {
        String phone = TestData.numberGenerator();
        fortraderPage.accountRegistration("Testq", "Testa", TestData.emailGenerator(), "381",
                phone, "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above");
        driver.get("https://www.fortrader.com/minilps/en/pro-dark-2024-dlp/");
        fortraderPage.firstStepWidget("Testq", "Testa", TestData.emailGenerator(), "381", phone);
        fortraderPage.assertPopUpAlreadyRegisteredAccount();
        fortraderPage.takeScreenshot("An already registered phone - FortradeR");
    }

    @Test
    protected void anAlreadyRegisteredEmailAndPhone() throws IOException {
        String email = TestData.emailGenerator();
        String phone = TestData.numberGenerator();
        fortraderPage.accountRegistration("Testq", "Testa", email,"381",
                phone, "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above");
        driver.get("https://www.fortrader.com/minilps/en/pro-dark-2024-dlp/");
        fortraderPage.firstStepWidget("Testq", "Testa",email, "381", phone);
        fortraderPage.assertPopUpAlreadyRegisteredAccount();
        fortraderPage.takeScreenshot("An already registered email and phone - FortradeR");
    }

    @Test
    protected void sameFNameAndLName() throws IOException {
        fortraderPage.firstStepWidget("Testq","Testq",TestData.emailGenerator(),"381",TestData.numberGenerator());
        fortraderPage.assertSameNameErrorMsg();
        fortraderPage.takeScreenshot("Error messages for the same first and last name - FortradeR");
    }

    @AfterMethod
    public void tearDown() {
        stopAppium();
    }
}
