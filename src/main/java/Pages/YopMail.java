package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class YopMail {
    /**
     * PageFactory- koristi se za direktno kreiranje web elemenata. Omogucava nam da sacuvamo veb element bez koricenja
     * chromeDriver.findElement(); Locira elemente na prvi poziv elementa za razliku od chromeDriver.findElement-a koji bi prijavljivao
     * noSuchElementException gresku.
     * Svaka stranica u svom konstuktoru ce sadrzati page factory.
     *
     * @param chromeDriver
     */

    public WebDriver chromeDriver;
    public WebDriverWait wait;
    public YopMail(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        PageFactory.initElements(chromeDriver,this);
    }
    @FindBy(xpath = "//input[@class='ycptinput']")
    public WebElement search;

    @FindBy(xpath = "//div[@id='refreshbut']")
    public WebElement goBtn;

    @FindBy(xpath = "//iframe[@id='ifinbox']")
    public WebElement inboxFrame;

    @FindBy(xpath = "//iframe[@id='ifmail']")
    public WebElement mailFrame;

    @FindBy(xpath = "//div[@class='lmfd']/span[contains(text(), 'Fortrade')]")
    public WebElement emailMessage;

    @FindBy(xpath = "//button[@id='refresh']")
    public WebElement refreshEmailBtn;

    @FindBy(xpath = "//span[contains(text(), 'Fortrade <ftadmin@fortrade.com>')]")
    public WebElement fortradeEmail;

    @FindBy(xpath = "//tr/td/b[contains(text(), 'Testq')]")
    public WebElement testqName;

    @FindBy(xpath = "//td[contains(text(),'KapitalRS')][1]")
    public WebElement emailMessageKRS;

    @FindBy(xpath = "//div[contains(text(),'Your Fortrade Demo Account Is Ready – Let’s GetStarted')]")
    public WebElement emailTitle;

    @FindBy(xpath = "//div[contains(text(),'Čestitamo! Uspešno ste otvorili demo račun za trgo...')]")
    public WebElement emailTitleKRS;

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


    public void findEmail(String emailValue){
        typeText(search,emailValue,"search input");
        clickElement(goBtn,"go button");/*
        wait.until(ExpectedConditions.visibilityOf(emailMessage));
        wait.until(ExpectedConditions.elementToBeClickable(emailMessage));*/
        for (int i = 0; i < 3; i++) {
            try {
                chromeDriver.switchTo().frame(inboxFrame);
                //WebElement emailMessage = chromeDriver.findElement(By.xpath("//div[@class='lmfd']/span[contains(text(), 'Fortrade')]"));
                if (emailMessage.isDisplayed()) {
                    break; // Exit loop when message appears
                }
            } catch (NoSuchElementException e) {
                // Do nothing — element not yet found
                if (i==2){
                    Assert.fail("An email is not found after 3 tries!!!");
                }
            }

            chromeDriver.switchTo().defaultContent();
            clickElement(refreshEmailBtn, "refresh email inbox");
        }
        clickElement(emailMessage,"received message in mailbox");
        chromeDriver.switchTo().defaultContent();
        chromeDriver.switchTo().frame(mailFrame);
        Assert.assertEquals(getText(fortradeEmail, "getting fortrade email"), "Fortrade <ftadmin@fortrade.com>");
        Assert.assertEquals(getText(testqName, "getting Testq name"), "Testq");
    }

    public void findEmailKRS(String emailValue){
        typeText(search,emailValue,"search input");
        clickElement(goBtn,"go button");
        wait.until(ExpectedConditions.visibilityOf(emailMessageKRS));
        wait.until(ExpectedConditions.elementToBeClickable(emailMessageKRS));
        clickElement(emailMessageKRS,"received message in mailbox");
    }

    /**
     * zoomOutMethod() method is used to help the takeScreenshot() method to capture the whole email message (header,
     * body, and footer)
     * @throws AWTException
     */
    public void zoomOutMethod() throws AWTException {
        Robot robot = new Robot();
        for (int i = 0; i < 5; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_CONTROL);
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

}
