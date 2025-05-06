package TestsFortrade;

import ConfigureAppium.BaseTest;
import Faker.TestData;
import Pages.BasePage;
import Pages.CrmPage;
import Pages.FortradePage;
import Pages.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?" + tag + "&fts=age-annual-saving-knowledge");
        waitForElement(driver.findElement(By.xpath("//input[@id='PhoneCountryCode']")), "0");
        fortradePage = new FortradePage((AndroidDriver) driver);
    }
    
    @AfterMethod
    protected void tearDown() {
        stopAppium();
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

    @Test
    @Parameters({"tag", "countryCode", "regulation"})
    public void checkingTagsInTheCrm(String tag, String countryCode, String regulation) throws IOException, AWTException {
        String email = TestData.emailGenerator();
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=age-annual-saving-knowledge&tg=ivanA" +
                "1434&tag1=ivanB@1434&tag2=ivanL1434&tag3=ivanM1434&gid=ivanC@1434&G_GEO=ivanD1434&G_GEOint=ivanE1434&G_" +
                "Device=ivanF1434&G_DeviceModel=ivanG1434&G_AdPos=ivanH1434&g_Track=ivanI1434&Track=ivanj1434&gclid=ivanK1434&" + tag);
        fortradePage.accountRegistration("Testq", "Testa", email, countryCode,
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", regulation);
        crmPage.takeScreenshot("Account details Fortrade page " + regulation, crmPage.accFullNameCrm);
        crmPage.takeScreenshot("SMS Verification field - no value" + regulation, crmPage.smsVerification);
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field without sms parameter - no value " + regulation, crmPage.smsVerification);
        crmPage.checkCrmTags();
        crmPage.takeScreenshot("Marketing tags Fortrade page " + regulation, crmPage.accFullNameCrm);
        stopWebBrowser();
    }

    @Test
    @Parameters({"tag", "countryCode", "regulation"})
    public void checkingAgeParameter(String tag, String countryCode, String regulation) throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=age&" + tag);
        fortradePage.ageParameter("Testq", "Testa", email, countryCode, TestData.numberGenerator(),
                "25-34");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", regulation);
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field Age parameter - no value " + regulation, crmPage.smsVerification);
        crmPage.checkLinkIdValue("25_34_age,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot( "Age parameter value "+regulation, crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    @Parameters({"tag", "countryCode", "regulation"})
    public void checkingAnnualParameter(String tag, String countryCode, String regulation) throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=annual&" + tag);
        fortradePage.annualParameter("Testq", "Testa", email, countryCode, TestData.numberGenerator(),
                "$15,000-$50,000");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", regulation);
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field Annual parameter - no value " + regulation, crmPage.smsVerification);
        crmPage.checkLinkIdValue("15000_50000_annual,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot( "Annual parameter value "+regulation, crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    @Parameters({"tag", "countryCode", "regulation"})
    public void checkingSavingParameter(String tag, String countryCode, String regulation) throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=saving&" + tag);
        fortradePage.savingParameter("Testq", "Testa", email, countryCode, TestData.numberGenerator(),
                "$50,000 – $100,000");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", regulation);
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field Saving parameter - no value " + regulation, crmPage.smsVerification);
        crmPage.checkLinkIdValue("50000_100000_savings,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot( "Saving parameter value " + regulation, crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    @Parameters({"tag", "countryCode", "regulation"})
    public void checkingKnowledgeParameter(String tag, String countryCode, String regulation) throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=knowledge&" + tag);
        fortradePage.knowledgeParameter("Testq", "Testa", email, countryCode, TestData.numberGenerator(),
                "All the above");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", regulation);
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field Knowledge parameter - no value " + regulation, crmPage.smsVerification);
        crmPage.checkLinkIdValue("knowledge_of_trading_all_the_above,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot( "Knowledge parameter value "+regulation, crmPage.linkId);
        stopWebBrowser();
    }

}
