package Pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class MailinatorPage {
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
    public MailinatorPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        PageFactory.initElements(chromeDriver,this);
    }
    @FindBy(xpath = "//input[@id='search']")
    public WebElement search;

    @FindBy(xpath = "//button[contains(text(),'GO')]")
    public WebElement goBtn;

    @FindBy(xpath = "//td[contains(text(),'Fortrade')][1]")
    public WebElement emailMessage;

    @FindBy(xpath = "//td[contains(text(),'KapitalRS')][1]")
    public WebElement emailMessageKRS;

    @FindBy(xpath = "//div[contains(text(),'Welcome to Fortrade')]")
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


    public void findEmail(String emailValue){
        typeText(search,emailValue,"search input");
        clickElement(goBtn,"go button");
        wait.until(ExpectedConditions.visibilityOf(emailMessage));
        wait.until(ExpectedConditions.elementToBeClickable(emailMessage));
        clickElement(emailMessage,"received message in mailbox");
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
