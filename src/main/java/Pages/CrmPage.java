package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CrmPage {
    public WebDriver chromeDriver;
    public WebDriverWait wait;
    public CrmPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        PageFactory.initElements(chromeDriver,this);
    }

    @FindBy(xpath = "//div[@id='lv_custom_tag']")
    WebElement customTag;

    @FindBy(xpath = "//input[@id='userNameInput']")
    public WebElement usernameCrm;

    @FindBy(xpath = "//input[@id='passwordInput']")
    public WebElement passwordCrm;

    @FindBy(xpath = "//div[@id='submissionArea']//span[@id='submitButton']")
    public WebElement signInCrm;

    @FindBy(xpath = "//iframe[@id='InlineDialog_Iframe']")
    public WebElement iFrameMicrosoftCrm;

    @FindBy(xpath = "//div[@id='navTourCloseButtonImage']//img[@alt='Close']")
    public WebElement closeMicrosoftCrm;

    @FindBy(xpath = "//iframe[@id='contentIFrame0']")
    public WebElement iFrameSearch;

    @FindBy(xpath = "//input[@id='crmGrid_findCriteria']")
    public WebElement searchInCrm;

    ////div[@style='height:100%']//input[@id='crmGrid_findCriteria']

    @FindBy(xpath = "//a[@id='crmGrid_findCriteriaButton']//img[@id='crmGrid_findCriteriaImg']")
    public WebElement searchBtnCrm;

    @FindBy(xpath = "//div[@id='crmGrid_divDataArea']//tr[@class='ms-crm-List-Row']/td[3]")
    public WebElement accountCrm;

    @FindBy(xpath = "//iframe[@id='contentIFrame1']")
    public WebElement iFrameAccDetails;

    @FindBy(xpath = "//div[@id='FormTitle']//h1[@title='Testq Testa']")
    public WebElement accFullNameCrm;

    @FindBy(xpath = "//div[@class='ms-crm-Inline-Value ms-crm-HeaderTile']")
    public WebElement accDemoField;

    @FindBy(xpath = "//h2[@id='tab_4_header_h2' and contains(text(),'Environment & Marketing Info')]")
    public WebElement marketingSection;

    @FindBy(xpath = "//a[@id='FormSecNavigationControl-Icon']")
    public WebElement menuBtn;

    @FindBy(xpath = "//table[@id='flyoutFormSection_ContentTable']/..//td[@title='Environment & Marketing Info']")
    public WebElement envAndMarSec;

    @FindBy(xpath = "//div[@id='crmFormHeaderTop']")
    public WebElement borderTopColorForRegulation;

    @FindBy(xpath = "//div[@id='lc_sms_verification']/div//span")
    public WebElement smsVerification;

    @FindBy(xpath = "//div[@id='lv_linkid']//div//span")
    public WebElement linkId;


    public void clickElement(WebElement element, String log) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(chromeDriver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(chromeDriver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        }
    }
    public void typeText(WebElement element, String text, String log) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " into " + log + " field");
        } catch (StaleElementReferenceException e) {
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " into " + log + " field");
        }
    }
    public String readAttribute(WebElement element, String attribute, String log) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));

            System.out.println("Get text from " + log + " element");
            return element.getAttribute(attribute);
        } catch (StaleElementReferenceException e) {

            System.out.println("Get text from " + log + " element");
            return element.getAttribute(attribute);
        }
    }
    public String getText(WebElement element, String log) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));

            System.out.println("Get text from " + log + " element");
            return element.getText();
        } catch (StaleElementReferenceException e) {

            System.out.println("Get text from " + log + " element");
            return element.getText();
        }
    }
    public void doubleClick(WebElement element, String log) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(chromeDriver);
            actions.moveToElement(element).doubleClick().build().perform();
            System.out.println("Double clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(chromeDriver);
            actions.moveToElement(element).doubleClick().build().perform();
            System.out.println("Double clicked " + log);
        }
    }
    public void takeScreenshot(String fileName, WebElement element) throws AWTException, IOException {

        FluentWait<WebDriver> wait = new FluentWait<>(chromeDriver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        wait.until(ExpectedConditions.visibilityOf(element));

        // Using java.awt.Robot and java.awt.Dimension for full screen capture
        Robot robot = new Robot();
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Rectangle screenRect = new java.awt.Rectangle(screenSize);
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

        // Saving the full screen image
        ImageIO.write(screenFullImage, "PNG", new File("src/screenshots/" + fileName + ".png"));
    }

    public void takeScreenshot(String fileName) throws AWTException, IOException {

        // Using java.awt.Robot and java.awt.Dimension for full screen capture
        Robot robot = new Robot();
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Rectangle screenRect = new java.awt.Rectangle(screenSize);
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

        // Saving the full screen image
        ImageIO.write(screenFullImage, "PNG", new File("src/screenshots/" + fileName + ".png"));
    }

    public void scrollToAnElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0,300);");
        System.out.println("Scrolled to the " + element.getText());
    }

    public void logInCrm(String username, String password) {
        typeText(usernameCrm, username, "username for CRM");
        typeText(passwordCrm, password, "password for CRM");
        clickElement(signInCrm, "sign in to CRM");
    }

    public void loopForTagsCrm() {
        String number = "1434";
        String[] tags = {"lv_tag", "lv_tag1", "lv_googleid", "lv_tlid",
                "lv_tag2", "lv_tag3", "lv_g_track", "lv_gdevice", "lv_g_geo", "lv_g_geoint", "lv_g_device_model", "lv_g_adpos"};
        String[] valueOfTags = {"ivanA" + number, "ivanB@" + number, "ivanC@" + number, "ivanK" + number,
                "ivanL" + number, "ivanM" + number, "ivanI" + number, "ivanF" + number,
                "ivanD" + number, "ivanE" + number, "ivanG" + number, "ivanH" + number};
        for (int i = 0; i < tags.length; i++) {
            tagsInTheCrm(tags[i], valueOfTags[i]);
        }
    }
    public void loopForAccDetailsCrm(String email) {
        String[] tags = {"lv_firstname", "lv_lastname", "emailaddress1"};
        String[] valueOfTags = {"Testq", "Testa", email};
        for (int i = 0; i < tags.length; i++) {
            tagsInTheCrm(tags[i], valueOfTags[i]);
        }
    }

    public void tagsInTheCrm(String tag, String value) {
        String valueOfTag = readAttribute(chromeDriver.findElement(By.xpath("//div[@id='{Tag}']".replace("{Tag}", tag))), "title", "tag");
        System.out.println("This is the value of the " + tag + ": " + value);
        Assert.assertEquals(valueOfTag, value);
    }

    public void checkCrmData(String email, String fullName, String regulation){
        chromeDriver.get(System.getenv("URLForCrm"));
        logInCrm(System.getenv("UsernameForCrm"), System.getenv("PasswordForCrm"));
        chromeDriver.switchTo().frame(iFrameMicrosoftCrm);
        try {
            clickElement(closeMicrosoftCrm, "close Microsoft window");
        } catch (Exception e) {
            System.out.println(e);
        }
        chromeDriver.switchTo().defaultContent();
        chromeDriver.switchTo().frame(iFrameSearch);
        typeText(searchInCrm, email, "search bar for email in CRM");
        clickElement(searchBtnCrm, "search button in CRM");
        doubleClick(accountCrm,"account row");
        chromeDriver.switchTo().defaultContent();
        chromeDriver.switchTo().frame(iFrameAccDetails);
        assertBorderColorInCRM(regulation);
        Assert.assertEquals(readAttribute(accFullNameCrm, "title", "full name"), fullName);
        Assert.assertEquals(getText(accDemoField, "demo account field"), "Demo Registered");
        loopForAccDetailsCrm(email);
    }
    public void checkCrmTags(){
        clickElement(menuBtn, "menu button");
        clickElement(envAndMarSec, "environment and marketing section button");
        loopForTagsCrm();
    }

    public void checkCrmFtsQuery(String value){
        clickElement(menuBtn, "menu button");
        clickElement(envAndMarSec, "environment and marketing section button");
        String customTagText = readAttribute(customTag, "title", "tag");
        System.out.println("This is the value of the " + customTag + ": " + customTagText);
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            System.out.println(e);
        }
        scrollToAnElement(customTag);
        Assert.assertEquals(customTagText, value);
    }

    public void checkSMSVerification(String smsVerificationValue) {
        String smsVerificationVal = getText(smsVerification,"SMS Verification field from the CRM");
        Assert.assertEquals(smsVerificationVal,smsVerificationValue);
    }

    public void checkLinkIdValue(String linkIdValue){
        CrmPage crmPage = new CrmPage(chromeDriver);
        crmPage.clickElement(menuBtn, "menu button");
        crmPage.clickElement(envAndMarSec, "environment and marketing section button");
        String linkIdVal = getText(linkId,"Link ID field from the CRM");
        System.out.println("Link ID field value from the CRM "+linkIdVal);
        Assert.assertEquals(linkIdVal,linkIdValue);
    }
    public void assertBorderColorInCRM (String regulation){
        String borderColor = "rgb(255, 192, 203)";
        switch (regulation.toLowerCase()){
            case "fca":
                borderColor = "rgb(128, 0, 128)";
                break;
            case "iiroc":
                borderColor = "rgb(165, 42, 42)";
                break;
            case "asic":
                borderColor = "rgb(0, 128, 0)";
                break;
            case "cysec":
                borderColor = "";
                break;
        }
        wait.until(cssValueToBe(borderTopColorForRegulation, "border-color", borderColor));
        System.out.println("This is the border color: " + borderTopColorForRegulation.getCssValue("border-color"));
        Assert.assertEquals(borderTopColorForRegulation.getCssValue("border-color"), borderColor);
    }
    public ExpectedCondition<Boolean> cssValueToBe(final WebElement locator, final String cssProperty, final String expectedValue) {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver chromeDriver) {
                String actualValue = locator.getCssValue(cssProperty);
                return actualValue.equals(expectedValue);
            }

            @Override
            public String toString() {
                return String.format("value of CSS property '%s' to be '%s'", cssProperty, expectedValue);
            }
        };
    }
}
