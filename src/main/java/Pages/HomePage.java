package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;

public class HomePage extends BasePage {
    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyButtonDecline']")
    protected WebElement denyBtn;

    @FindBy(xpath = "//div[@class='exitButton']")
    protected WebElement notResidentBtn;

    @FindBy(xpath = "//div[@class='startTradingButton']")
    protected WebElement usePasswordBtn;

    @FindBy(xpath = "//iframe[@id='myWidgetIframe']")
    public WebElement iFrameIConsent;

    @FindBy(xpath = "//input[@value='I Consent']")
    protected WebElement iConsentBtn;

    @FindBy(xpath = "//div[@class='webSpriteIcon cross']")
    protected WebElement closeBtnPyc;

    @FindBy(xpath = "//div[@data-cmd='menu']")
    protected WebElement menuBtn;

    @FindBy(xpath = "//div[@id='platformRegulation']")
    public WebElement regulationMsg;

    @FindBy(xpath = "//span[text()='Fortrade']")
    public WebElement fortradeLogo;

    public void clickDenyBtn() {
        clickElement(denyBtn, "deny cookies button");
    }

    public void clickNotResBtn() {
        clickElement(notResidentBtn, "I am not Serbian resident");
    }

    public void clickUsePassBtn() {
        clickElement(usePasswordBtn, "Use Password button");
    }

    public void clickConsentBtn() {
        driver.switchTo().frame(iFrameIConsent);
        try {
            clickElement(iConsentBtn, "I consent button");
        } catch (Exception e) {
            System.out.println("Could not switch to frame - " + e);
        }
        driver.switchTo().defaultContent();
    }

    public void closePersonalizeYourContent(){
        driver.switchTo().frame(iFrameIConsent);
        try {
            clickElement(closeBtnPyc,"- close personalize your content pop-up button");
        } catch (Exception e) {
            System.out.println("Could not switch to frame - " + e);
        }
        driver.switchTo().defaultContent();
    }

    public void clickMenuBtn() {
        clickElement(menuBtn, "Menu button");
    }

    public void checkRegulation(String regulation) throws IOException, AWTException {
        String actualText = getText(regulationMsg, "regulation text field");
        switch (regulation) {
            case "FCA": {
                Assert.assertEquals(actualText, "Broker: Fortrade Ltd. (FCA)");
                new BasePage(driver).takeScreenshot("Broker Fortrade Ltd FCA - successfully registered demo account", regulationMsg);
            }
            break;
            case "cyses": {
                Assert.assertEquals(actualText, "Broker: Fortrade Cyprus Ltd. (CySec)");
                new BasePage(driver).takeScreenshot("Broker Fortrade Cyprus Ltd CySec - successfully registered demo account", regulationMsg);
            }
            break;
            case "Asic": {
                Assert.assertEquals(actualText, "Broker: Fort Securities Australia Pty Ltd. (ASIC)");
                new BasePage(driver).takeScreenshot("Broker Fort Securities Australia Pty Ltd ASIC - successfully registered demo account", regulationMsg);
            }
            break;
            case "iiroc": {
                Assert.assertEquals(actualText, "Broker: Fortrade Canada Limited (CIRO)");
                new BasePage(driver).takeScreenshot("Broker Fortrade Canada Limited CIRO - successfully registered demo account", regulationMsg);
            }
            break;
            case "FSC":
            default: {
                Assert.assertEquals(actualText, "Broker: Fortrade (Mauritius) Ltd (FSC)");
                new BasePage(driver).takeScreenshot("Broker Fortrade Mauritius Ltd FSC - successfully registered demo account", regulationMsg);
            }
            break;
        }
        System.out.println(regulationMsg.getText());
    }

}
