package TestsFortrade;

import ConfigureAppium.BaseTest;
import Faker.TestData;

import Pages.CrmPage;
import Pages.FortradePage;
import Pages.HomePage;
import Pages.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import java.time.Duration;

public class pro_Dark_2024_Lp extends BaseTest {

    FortradePage fortradePage;
    HomePage homePage;

    @Parameters({"tag"})
    @BeforeMethod
    protected void setup(String tag) throws MalformedURLException, URISyntaxException {
        configureAppium();
        fortradePage = new FortradePage((AndroidDriver) driver);
        homePage = new HomePage((AndroidDriver) driver);
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?" + tag + "&fts=age-annual-saving-knowledge-plang:all");
        waitForElement(driver.findElement(By.xpath("//input[@id='PhoneCountryCode']")), "0");
        fortradePage.clickDenyBtn();
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
                "$50,000 – $100,000", "All the above", "Serbian");
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
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?" + tag + "&fts=sms-age-annual-saving-knowledge-plang:all");
        fortradePage.unsuccessfullyRegistrationWrongSMS("Testq", "Testa", TestData.emailGenerator(), countryCode,
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above", "Serbian",
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
                "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above", "Serbian");
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?" + tag);
        fortradePage.firstStepWidget("Testq", "Testa", email, countryCode, TestData.numberGenerator());
        fortradePage.assertPopUpAlreadyRegisteredAccount();
        fortradePage.takeScreenshot("An already registered email address - " + regulation + " regulation");
    }

    @Parameters({"countryCode", "tag", "regulation"})
    @Test
    protected void anAlreadyRegisteredPhone(String countryCode, String tag, String regulation) throws IOException {
        String phone = TestData.numberGenerator();
        fortradePage.accountRegistration("Testq", "Testa", TestData.emailGenerator(), countryCode,
                phone, "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above", "Serbian");
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?" + tag);
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
                phone, "25-34", "$50,000-$100,000", "$50,000 – $100,000", "All the above", "Serbian");
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?" + tag);
        fortradePage.firstStepWidget("Testq", "Testa",email, countryCode, phone);
        fortradePage.assertPopUpAlreadyRegisteredAccount();
        fortradePage.takeScreenshot("An already registered email and phone - " + regulation + " regulation");
    }

    @Parameters({"countryCode","regulation"})
    @Test
    protected void sameFNameAndLName(String countryCode,String regulation) throws IOException {
        fortradePage.firstStepWidget("Testq","Testq",TestData.emailGenerator(),countryCode,TestData.numberGenerator());
        fortradePage.assertSameNameErrorMsg();
        fortradePage.takeScreenshot("Error messages for the same first and last name - " + regulation + " regulation");
    }

    @Test
    @Parameters({"tag", "countryCode", "regulation"})
    public void checkingTagsInTheCrm(String tag, String countryCode, String regulation) throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=age-annual-saving-knowledge-plang:all&tg=ivanA" +
                "1434&tag1=ivanB@1434&tag2=ivanL1434&tag3=ivanM1434&gid=ivanC@1434&G_GEO=ivanD1434&G_GEOint=ivanE1434&G_" +
                "Device=ivanF1434&G_DeviceModel=ivanG1434&G_AdPos=ivanH1434&g_Track=ivanI1434&Track=ivanj1434&gclid=ivanK1434&" + tag);
        fortradePage.accountRegistration("Testq", "Testa", email, countryCode,
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above", "Serbian");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", regulation);
        crmPage.takeScreenshot("Account details Fortrade page " + regulation, crmPage.accFullNameCrm);
        crmPage.takeScreenshot("SMS Verification field - no value" + regulation, crmPage.smsVerification);
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field without sms parameter - no value " + regulation, crmPage.smsVerification);
        crmPage.checkCrmTags();
        crmPage.takeScreenshot("Marketing tags Fortrade page " + regulation, crmPage.accFullNameCrm);
        crmPage.checkLinkIdValue("25_34_age,15000_50000_annual,50000_100000_savings,knowledge_of_trading_all_the_above,lang_SR,M-android");
        stopWebBrowser();
    }

    @Test
    @Parameters({"tag", "countryCode", "regulation"})
    public void checkingAgeParameter(String tag, String countryCode, String regulation) throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=age&" + tag);
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
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=annual&" + tag);
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
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=saving&" + tag);
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
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=knowledge&" + tag);
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

    @Test
    @Parameters({"tag", "countryCode", "regulation"})
    public void checkingPLangParameter(String tag, String countryCode, String regulation) throws IOException, AWTException, InterruptedException {
        String email = TestData.emailGenerator();
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=plang:all&" + tag);
        fortradePage.pLangParameter("Testq", "Testa", email, countryCode, TestData.numberGenerator(),
                "Serbian");
        startChromeBrowserOnDesktop();
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.checkCrmData(email, "Testq Testa", regulation);
        crmPage.checkSMSVerification("--");
        crmPage.takeScreenshot("SMS Verification field PLang parameter - no value " + regulation, crmPage.smsVerification);
        crmPage.checkLinkIdValue("lang_SR,M-android");
        Thread.sleep(1000);
        crmPage.takeScreenshot( "PLang parameter value " + regulation, crmPage.linkId);
        stopWebBrowser();
    }

    @Test
    public void checkFcaRedirection() throws IOException {
        fortradePage.clickFcaLink();
        fortradePage.assertUrl(fortradePage.fcaURL);
        fortradePage.takeScreenshot("Financial Conduct Authority - FCA regulation");
    }

    @Test
    public void checkIirocRedirection() throws IOException {
        fortradePage.clickIirocLink();
        fortradePage.assertUrl(fortradePage.iirocURL);
        fortradePage.takeScreenshot("Canadian Investor Protection Fund (CIPF) - Iiroc regulation");
    }

    @Test
    public void checkAsicRedirection() throws IOException {
        fortradePage.clickAsicLink();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.urlContains("https://connectonline.asic.gov.au/"));
        fortradePage.takeScreenshot("Australian Securities and Investments Commission - ASIC regulation");
    }

    @Test
    public void checkCysecRedirection() throws IOException {
        fortradePage.clickCysecLink();
        fortradePage.assertUrl(fortradePage.cysecURL);
        fortradePage.takeScreenshot("Cyprus Securities and Exchange Commission - CySEC regulation");
    }

    @Test
    public void checkFscRedirection() throws IOException {
        fortradePage.clickFscLink();
        fortradePage.assertUrl(fortradePage.fscURL);
        fortradePage.takeScreenshot("Financial Services Commission Mauritius FSC - regulation");
    }

    //This method is for Mailinator (now it's not working, so we use Yopmail)
    /*@Test
    @Parameters({"regulation", "countryCode"})
    public void emailIsReceivedSuccessfully(String regulation, String countryCode) throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortradePage.accountRegistration("Testq", "Testa", email, countryCode, TestData.numberGenerator(),
                "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above", "Serbian");
        startChromeBrowserOnDesktop();
        MailinatorPage mailinator = new MailinatorPage(chromeDriver);
        chromeDriver.get("https://www.mailinator.com/");
        mailinator.findEmail(email);
        mailinator.zoomOutMethod();
        mailinator.takeScreenshot("Email is received successfully - " + regulation + " regulation", mailinator.emailTitle);
        stopWebBrowser();
    }*/

    @Test
    @Parameters({"regulation", "countryCode"})
    public void emailIsReceivedSuccessfully(String regulation, String countryCode) throws IOException, AWTException {
        String email = TestData.emailGenerator();
        fortradePage.accountRegistration("Testq", "Testa", email, countryCode, TestData.numberGenerator(),
                "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above", "Serbian");
        startChromeBrowserOnDesktop();
        YopMail yopMail = new YopMail(chromeDriver);
        chromeDriver.get("https://yopmail.com/en/");
        yopMail.findEmail(email);
        yopMail.zoomOutMethod();
        yopMail.takeScreenshot("Email is received successfully - " + regulation + " regulation");
        stopWebBrowser();
    }

    @Test
    @Parameters({"tag","countryCode","regulation"})
    public void didNotGetToken(String tag,String countryCode,String regulation) throws InterruptedException, IOException {
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?" + tag + "&fts=sms-age-annual-saving-knowledge");
        fortradePage.tokenIsNotReceived("Testq", "Testa", TestData.emailGenerator(), countryCode,
                TestData.numberGenerator(), "25-34", "$15,000-$50,000", "$50,000 – $100,000",
                "All the above");
        if (fortradePage.codeIsSent.isDisplayed()) {
            Assert.assertEquals(fortradePage.codeIsSent.getText(), "We sent you the code again");
            fortradePage.takeScreenshot("We sent you the code again - " + regulation);
        }
    }

    @Test
    @Parameters({"tag","countryCode","regulation"})
    public void userIsReturnedTo1stWidget(String tag,String countryCode,String regulation) throws IOException {
        driver.get("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?" + tag + "&fts=sms-age-annual-saving-knowledge");
        fortradePage.returnToThe1stWidget("Testq", "Testa", TestData.emailGenerator(), countryCode,
                TestData.numberGenerator());
        fortradePage.takeScreenshot("The user is returned to the 1st form widget - "+regulation, fortradePage.alrHaveAccount);
    }

    @Test
    @Parameters({"regulation"})
    public void emptyDataAccountRegistration(String regulation) throws IOException {
        fortradePage.unsuccessfullyRegistrationWrongData("","","","",
                "");
        fortradePage.assertErrorMessages();
        fortradePage.assertColor("red");
        fortradePage.takeScreenshot("Demo account registration - no data - "+ regulation);
    }

    @Test
    @Parameters({"regulation","tag"})
    public void checkLogoClickability(String regulation, String tag) throws IOException {
        fortradePage.clickLogo(regulation,"https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?"+tag+"&fts=age-annual-saving-knowledge-plang:all");
        fortradePage.takeScreenshot("Logo is not clickable "+regulation);
    }

    @Test
    @Parameters("regulation")
    public void checkCountryCodeErrorMsg(String regulation) throws IOException {
        fortradePage.firstStepWidget("Testq","Testa",TestData.emailGenerator(),"/*@#$",
        TestData.numberGenerator());
        Assert.assertEquals(fortradePage.getText(fortradePage.countryCodeErrorMessage,
                "Country code field error message : "+fortradePage.countryCodeErrorMessage.getText()),
                "Please enter a valid country code");
        fortradePage.takeScreenshot("Country code error message "+regulation);
    }

    @Test
    @Parameters({"regulation"})
    public void checkLoginRedirection(String regulation) {
        fortradePage.clickAlrHaveAnAcc();
        try {
            if (driver.getCurrentUrl().contains(fortradePage.appUrl) && homePage.fortradeLogo.isDisplayed()) {
                fortradePage.takeScreenshot("Login redirection-user is redirected to the app " + regulation, homePage.fortradeLogo);
            }
        } catch (Exception e) {
            System.out.println(e + "Wrong link redirection");
        }
    }

    @Test
    @Parameters({"tag","countryCode","regulation"})
    public void errorMessageAgeParameter(String tag,String countryCode ,String regulation) throws IOException,InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=age&"+tag);
        fortradePage.ageParameter("Testq", "Testa", email, countryCode, phoneNumber,
                "-- Select --");
        fortradePage.secondStepErrorMessage(1);
        fortradePage.takeScreenshot("Age parameter error message - Fortrade - " + regulation);
    }

    @Test
    @Parameters({"tag", "regulation"})
    public void checkFCAPercentages(String tag, String regulation) throws IOException {
        fortradePage.checkPercentages("71% of retail investor accounts lose money when trading CFDs with this provider.");
        try {
            Thread.sleep(500);
        } catch (Exception e){
            System.out.println(e);
        }
        fortradePage.takeScreenshot("Percentages - " + regulation + " regulation");
    }

    @Test
    @Parameters({"tag", "regulation"})
    public void checkCysecPercentages(String tag, String regulation) throws IOException {
        fortradePage.checkPercentages("70.91% of retail investor accounts lose money when trading CFDs with this provider.");
        try {
            Thread.sleep(500);
        } catch (Exception e){
            System.out.println(e);
        }
        fortradePage.takeScreenshot("Percentages - " + regulation + " regulation");
    }

    @Test
    @Parameters({"tag","countryCode","regulation"})
    public void errorMessageAnnualParameter(String tag,String countryCode ,String regulation) throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=annual&"+tag);
        fortradePage.annualParameter("Testq", "Testa", email, countryCode, phoneNumber,
                "-- Select --");
        fortradePage.secondStepErrorMessage(1);
        fortradePage.takeScreenshot("Annual parameter error message - Fortrade - " + regulation + " regulation");
    }

    @Test
    @Parameters({"tag","countryCode","regulation"})
    public void errorMessageSavingParameter(String tag,String countryCode ,String regulation) throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=saving&"+tag);
        fortradePage.savingParameter("Testq", "Testa", email, countryCode, phoneNumber,
                "-- Select --");
        fortradePage.secondStepErrorMessage(1);
        fortradePage.takeScreenshot("Saving parameter error message - Fortrade - " + regulation + " regulation");
    }

    @Test
    @Parameters({"tag","countryCode","regulation"})
    public void errorMessageKnowledgeParameter(String tag,String countryCode ,String regulation) throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=knowledge&"+tag);
        fortradePage.knowledgeParameter("Testq", "Testa", email, countryCode, phoneNumber,
                "-- Select --");
        fortradePage.secondStepErrorMessage(1);
        fortradePage.takeScreenshot("Knowledge parameter error message - Fortrade - " + regulation + " regulation");
    }

    @Test
    @Parameters({"tag","countryCode","regulation"})
    public void errorMessagePLangParameter(String tag,String countryCode ,String regulation) throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortradePage.getDriver("https://www.fortrade.com/minilps/en/pro-dark-2024-dlp/?fts=plang:all&"+tag);
        fortradePage.pLangParameter("Testq", "Testa", email, countryCode, phoneNumber,
                "-- Select --");
        fortradePage.secondStepErrorMessage(1);
        fortradePage.takeScreenshot("PLang parameter error message - Fortrade - " + regulation + " regulation");
    }

    @Test
    @Parameters({"countryCode","regulation"})
    public void errorMessagesAllParameters(String countryCode ,String regulation) throws IOException, InterruptedException {
        String email = TestData.emailGenerator();
        String phoneNumber = TestData.numberGenerator();
        fortradePage.unsuccessfullyRegistration("Testq", "Testa", email, countryCode, phoneNumber,
                "25-34", "$15,000-$50,000", "$50,000 – $100,000", "All the above",
                "-- Select --", "-- Select --", "-- Select --", "-- Select --");
        fortradePage.secondStepErrorMessage(4);
        fortradePage.takeScreenshot("All parameters error message - Fortrade - " + regulation + " regulation");
    }

}
