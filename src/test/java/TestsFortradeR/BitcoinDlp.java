package TestsFortradeR;

import ConfigureAppium.BaseTest;
import Faker.TestData;
import Pages.CrmPage;
import Pages.FortradeRPage;
import Pages.HomePage;
import Pages.YopMail;
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

public class BitcoinDlp extends BaseTest {

    FortradeRPage fortraderPage;
    HomePage homePage;

    @BeforeMethod
    public void setup() throws MalformedURLException, URISyntaxException {
        configureAppium();
        fortraderPage = new FortradeRPage((AndroidDriver) driver);
        homePage = new HomePage((AndroidDriver) driver);
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge-plang:all");
        waitForElement(driver.findElement(By.xpath("//input[@id='PhoneCountryCode']")), "0");
    }

    @Test
    public void demoAccountRegistration() throws IOException, AWTException {

        //Start recording
        ((CanRecordScreen) driver).startRecordingScreen();
        fortraderPage.accountRegistration("Testq", "Testa", TestData.emailGenerator(),
                "381", TestData.numberGenerator(), "25-34", "$50,000-$100,000",
                "$50,000 – $100,000", "All the above", "Serbian");
        HomePage homePage = new HomePage((AndroidDriver) driver);
        //homePage.clickDenyBtn();
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
        fortraderPage.unsuccessfullyRegistrationWrongData("0000", "9999", "*+-", "asd",
                "pjqyQ");
        fortraderPage.assertErrorMessages();
        fortraderPage.assertColor("Red");
        fortraderPage.takeScreenshot("Unsuccessfully demo account registration-FortradeR");
    }

    @Test
    public void assertInvalidTokenMsg() throws IOException {
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=sms-age-annual-saving-knowledge-plang:all");
        fortraderPage.unsuccessfullyRegistrationWrongSMS("Testq", "Testa", TestData.emailGenerator(), "381",
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above", "Serbian",
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
                "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above", "Serbian");
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge-plang:all");
        fortraderPage.firstStepWidget("Testq", "Testa", email,"381", TestData.numberGenerator());
        fortraderPage.assertPopUpAlreadyRegisteredAccount();
        fortraderPage.takeScreenshot("An already registered email address - FortradeR");
    }

    @Test
    protected void anAlreadyRegisteredPhone() throws IOException {
        String phone = TestData.numberGenerator();
        fortraderPage.accountRegistration("Testq", "Testa", TestData.emailGenerator(), "381",
                phone, "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above", "Serbian");
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge-plang:all");
        fortraderPage.firstStepWidget("Testq", "Testa", TestData.emailGenerator(), "381", phone);
        fortraderPage.assertPopUpAlreadyRegisteredAccount();
        fortraderPage.takeScreenshot("An already registered phone - FortradeR");
    }

    @Test
    protected void anAlreadyRegisteredEmailAndPhone() throws IOException {
        String email = TestData.emailGenerator();
        String phone = TestData.numberGenerator();
        fortraderPage.accountRegistration("Testq", "Testa", email,"381",
                phone, "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above", "Serbian");
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge-plang:all");
        fortraderPage.firstStepWidget("Testq", "Testa",email, "381", phone);
        fortraderPage.assertPopUpAlreadyRegisteredAccount();
        fortraderPage.takeScreenshot("An already registered email and phone - FortradeR");
    }

    @Test
    protected void sameFNameAndLName() throws IOException {
        fortraderPage.firstStepWidget("Testq", "Testq", TestData.emailGenerator(), "381", TestData.numberGenerator());
        fortraderPage.assertSameNameErrorMsg();
        fortraderPage.takeScreenshot("Error messages for the same first and last name - FortradeR");
    }

    @Test
    public void checkingTagsInTheCrm() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge-plang:all&tg=ivanA" +
                "1434&tag1=ivanB@1434&tag2=ivanL1434&tag3=ivanM1434&gid=ivanC@1434&G_GEO=ivanD1434&G_GEOint=ivanE1434&G_" +
                "Device=ivanF1434&G_DeviceModel=ivanG1434&G_AdPos=ivanH1434&g_Track=ivanI1434&Track=ivanj1434&gclid=ivanK1434");
        fortraderPage.accountRegistration("Testq", "Testa", email, "381",
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above", "Serbian");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "FSC");
        crmPage.takeScreenshot("Account details Fortrader page", crmPage.accFullNameCrm);
        crmPage.checkCrmTags();
        crmPage.takeScreenshot("Marketing tags Fortrader page", crmPage.accFullNameCrm);
        crmPage.checkLinkIdValue("25_34_age,15000_50000_annual,50000_100000_savings,knowledge_of_trading_all_the_above,lang_SR,M-android");
        stopWebBrowser();
    }

    @Test
    public void checkingDummyFtsQueryInTheCrm() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge-plang:all&" +
                "ftsquery=age-equals(1,2)-and-[[annual-notequals(2,3)-or-saving-equals(3)]-and-[knowledge-equals(1,2)-or-plang-notequals(1)]]&" +
                "tg=ivanA1434&tag1=ivanB@1434&tag2=ivanL1434&tag3=ivanM1434&gid=ivanC@1434&G_GEO=ivanD1434&G_GEOint=ivanE1434&G_" +
                "Device=ivanF1434&G_DeviceModel=ivanG1434&G_AdPos=ivanH1434&g_Track=ivanI1434&Track=ivanj1434&gclid=ivanK1434");
        String url = driver.getCurrentUrl();
        fortraderPage.ftsQueryParameter(url, "Testq", "Testa", email, "381",
                TestData.numberGenerator(), "18-24", "Less than $15,000", "Less than $5,000",
                "Yes, from a relevant role in financial services", "Albanian");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "FSC");
        crmPage.checkCrmFtsQuery("Dummy");
        crmPage.takeScreenshot("Dummy custom tag - Fortrader page ", crmPage.accFullNameCrm);
    }

    @Test
    public void checkingEmptyFtsQueryInTheCrm() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge-plang:all&" +
                "ftsquery=age-equals(1,2)-and-[[annual-notequals(2,3)-or-saving-equals(3)]-and-[knowledge-equals(1,2)-or-plang-notequals(1)]]&" +
                "tg=ivanA1434&tag1=ivanB@1434&tag2=ivanL1434&tag3=ivanM1434&gid=ivanC@1434&G_GEO=ivanD1434&G_GEOint=ivanE1434&G_" +
                "Device=ivanF1434&G_DeviceModel=ivanG1434&G_AdPos=ivanH1434&g_Track=ivanI1434&Track=ivanj1434&gclid=ivanK1434");
        String url = driver.getCurrentUrl();
        fortraderPage.ftsQueryParameter(url, "Testq", "Testa", email, "381",
                TestData.numberGenerator(), "18-24", "Less than $15,000", "$25,000 – $50,000",
                "Yes, from previous trading experience", "Albanian");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "381");
        crmPage.checkCrmFtsQuery("");
        crmPage.takeScreenshot("Empty custom tag - Fortrader page ", crmPage.accFullNameCrm);
    }

    @Test
    public void checkingInvalidFtsQueryInTheCrm() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge&" +
                "ftsquery=age-equals(1,2)-and-[[annual-notequals(2,3)-or-saving-equals(3)]-and-[knowledge-equals(1,2)-or-plang-notequals(1)]]&" +
                "tg=ivanA1434&tag1=ivanB@1434&tag2=ivanL1434&tag3=ivanM1434&gid=ivanC@1434&G_GEO=ivanD1434&G_GEOint=ivanE1434&G_" +
                "Device=ivanF1434&G_DeviceModel=ivanG1434&G_AdPos=ivanH1434&g_Track=ivanI1434&Track=ivanj1434&gclid=ivanK1434");
        String url = driver.getCurrentUrl();
        fortraderPage.ftsQueryParameter(url, "Testq", "Testa", email, "381",
                TestData.numberGenerator(), "18-24", "Less than $15,000", "$25,000 – $50,000",
                "Yes, from a relevant role in financial services", "Arabic");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "381");
        crmPage.checkCrmFtsQuery("Invalid");
        crmPage.takeScreenshot("Invalid custom tag - Fortrader page ", crmPage.accFullNameCrm);
    }

    @Test
    public void checkingAgeParameter() throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age");
        fortraderPage.ageParameter("Testq", "Testa", email, "381", TestData.numberGenerator(),
                "25-34");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "FSC");
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field Age parameter - no value", crmPage.smsVerification);
        crmPage.checkLinkIdValue("25_34_age,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot("Age parameter value - FortradeR", crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    public void checkingAnnualParameter() throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=annual");
        fortraderPage.annualParameter("Testq", "Testa", email, "381", TestData.numberGenerator(),
                "$15,000-$50,000");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "FSC");
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field Annual parameter - no value", crmPage.smsVerification);
        crmPage.checkLinkIdValue("15000_50000_annual,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot("Annual parameter value - FortradeR", crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    public void checkingSavingParameter() throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=saving");
        fortraderPage.savingParameter("Testq", "Testa", email, "381", TestData.numberGenerator(),
                "$50,000 – $100,000");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "FSC");
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field Saving parameter - no value", crmPage.smsVerification);
        crmPage.checkLinkIdValue("50000_100000_savings,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot("Saving parameter value - FortradeR", crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    public void checkingKnowledgeParameter() throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=knowledge");
        fortraderPage.knowledgeParameter("Testq", "Testa", email, "381", TestData.numberGenerator(),
                "All the above");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "FSC");
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field Knowledge parameter - no value", crmPage.smsVerification);
        crmPage.checkLinkIdValue("knowledge_of_trading_all_the_above,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot("Knowledge parameter value - FortradeR", crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    public void checkingPLangParameter() throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=plang:all");
        fortraderPage.pLangParameter("Testq", "Testa", email, "381", TestData.numberGenerator(),
                "Serbian");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", "FSC");
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field PLang parameter - no value", crmPage.smsVerification);
        crmPage.checkLinkIdValue("lang_SR,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot( "PLang parameter value - FortradeR", crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    public void checkFscRedirection() throws IOException {
        fortraderPage.clickFscLink();
        fortraderPage.assertUrl(fortraderPage.fscURL);
        fortraderPage.takeScreenshot("Financial Services Commission Mauritius FSC - FortradeR");
    }

    //This method is for Mailinator (now it's not working, so we use Yopmail)
    /*@Test
    public void emailIsReceivedSuccessfully() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortraderPage.accountRegistration("Testq", "Testa", email,
                "381", TestData.numberGenerator(), "25-34", "$15,000-$50,000",
                "$50,000 – $100,000", "All the above", "Serbian");
        startChromeBrowserOnDesktop();
        MailinatorPage mailinator = new MailinatorPage(chromeDriver);
        chromeDriver.get("https://www.mailinator.com/");
        mailinator.findEmail(email);
        mailinator.zoomOutMethod();
        mailinator.takeScreenshot("Email is received successfully - FortradeR", mailinator.emailTitle);
        stopWebBrowser();
    }*/

    @Test
    public void emailIsReceivedSuccessfully() throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortraderPage.accountRegistration("Testq", "Testa", email,
                "381", TestData.numberGenerator(), "25-34", "$15,000-$50,000",
                "$50,000 – $100,000", "All the above", "Serbian");
        startChromeBrowserOnDesktop();
        YopMail yopMail = new YopMail(chromeDriver);
        chromeDriver.get("https://yopmail.com/en/");
        yopMail.findEmail(email);
        yopMail.zoomOutMethod();
        yopMail.takeScreenshot("Email is received successfully - FortradeR", yopMail.emailTitle);
        stopWebBrowser();
    }

    @Test
    public void didNotGetToken() throws InterruptedException, IOException {
        driver.get("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=sms-age-annual-saving-knowledge");
        fortraderPage.tokenIsNotReceived("Testq", "Testa", TestData.emailGenerator(), "381",
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000",
                "All the above");
        if (fortraderPage.codeIsSent.isDisplayed()) {
            Assert.assertEquals(fortraderPage.codeIsSent.getText(), "We sent you the code again");
            fortraderPage.takeScreenshot("We sent you the code again - FortradeR");
        }
    }

    @Test
    public void userIsReturnedTo1stWidget() throws IOException {
        driver.get("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=sms-age-annual-saving-knowledge");
        fortraderPage.returnToThe1stWidget("Testq", "Testa", TestData.emailGenerator(), "381",
                TestData.numberGenerator());
        fortraderPage.takeScreenshot("The user is returned to the 1st form widget - FortradeR");
    }

    @Test
    public void emptyDataAccountRegistration() throws IOException {
        fortraderPage.unsuccessfullyRegistrationWrongData("", "", "", "",
                "");
        fortraderPage.assertErrorMessages();
        fortraderPage.assertColor("red");
        fortraderPage.takeScreenshot("Demo account registration - no data - FortradeR");
    }

    @Test
    public void checkLogoClickability() throws IOException {
        fortraderPage.clickLogo("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age-annual-saving-knowledge-plang:all");
        fortraderPage.takeScreenshot("Logo is not clickable - FortradeR");
    }

    @Test
    public void checkCountryCodeErrorMsg() throws IOException {
        fortraderPage.firstStepWidget("Testq", "Testa", TestData.emailGenerator(), "/*@#$",
                TestData.numberGenerator());
        Assert.assertEquals(fortraderPage.getText(fortraderPage.countryCodeErrorMessage,
                        "Country code field error message : " + fortraderPage.countryCodeErrorMessage.getText()),
                "Please enter a valid country code");
        fortraderPage.takeScreenshot("Country code error message - FortradeR");
    }

    @Test
    public void checkLoginRedirection() {
        fortraderPage.clickAlrHaveAnAcc();
        try {
            if (driver.getCurrentUrl().contains(fortraderPage.proAppUrl) && homePage.fortradeLogo.isDisplayed()) {
                fortraderPage.takeScreenshot("Login redirection-user is redirected to the app -FortradeR");
            }
        } catch (Exception e) {
            System.out.println(e + "Wrong link redirection");
        }
    }

    @Test
    public void errorMessageAgeParameter() throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=age&");
        fortraderPage.ageParameter("Testq", "Testa", email, "381", phoneNumber,
                "-- Select --");
        fortraderPage.secondStepErrorMessage(1);
        fortraderPage.takeScreenshot("Age parameter error message - FortradeR");
    }

    @Test
    public void errorMessageAnnualParameter() throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=annual&");
        fortraderPage.annualParameter("Testq", "Testa", email, "381", phoneNumber,
                "-- Select --");
        fortraderPage.secondStepErrorMessage(1);
        fortraderPage.takeScreenshot("Annual parameter error message - FortradeR");
    }

    @Test
    public void errorMessageSavingParameter() throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=saving&");
        fortraderPage.savingParameter("Testq", "Testa", email, "381", phoneNumber,
                "-- Select --");
        fortraderPage.secondStepErrorMessage(1);
        fortraderPage.takeScreenshot("Saving parameter error message - FortradeR");
    }

    @Test
    public void errorMessageKnowledgeParameter() throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=knowledge&");
        fortraderPage.knowledgeParameter("Testq", "Testa", email, "381", phoneNumber,
                "-- Select --");
        fortraderPage.secondStepErrorMessage(1);
        fortraderPage.takeScreenshot("Knowledge parameter error message - FortradeR");
    }

    @Test
    public void errorMessagePLangParameter() throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortraderPage.getDriver("https://www.fortrader.com/minilps/en/bitcoin1-dlp/?fts=plang:all&");
        fortraderPage.pLangParameter("Testq", "Testa", email, "381", phoneNumber,
                "-- Select --");
        fortraderPage.secondStepErrorMessage(1);
        fortraderPage.takeScreenshot("PLang parameter error message - FortradeR");
    }

    @Test
    public void errorMessagesAllParameters() throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortraderPage.unsuccessfullyRegistration("Testq", "Testa", email, "381", phoneNumber,
                "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above",
                "-- Select --", "-- Select --", "-- Select --", "-- Select --");
        fortraderPage.secondStepErrorMessage(4);
        fortraderPage.takeScreenshot("All parameters error message - FortradeR");
    }

    @Test
    public void isCFDsTextDisplayedOnTheFortradeRPage(){
        Assert.assertFalse(fortraderPage.isTextVisibleAnywhereIgnoreCase("CFD"), "Text 'CFD' (case-insensitive) was found, but shouldn't be.");
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        stopAppium();
        stopWebBrowser();
    }

}
