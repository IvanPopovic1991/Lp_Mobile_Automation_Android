package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {

    protected AndroidDriver driver;
    WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickElement(WebElement element, String log) {
        for (int i = 0; i < 2; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                System.out.println("Clicked " + log);
                return;
            } catch (StaleElementReferenceException e) {
                element.click();
                System.out.println("Clicked " + log);
            }
        }
    }

    public void typeText(WebElement element, String text, String log) {
        for (int i = 0; i < 2; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                element.clear();
                element.sendKeys(text);
                System.out.println("Typed " + text + " into " + log + " field");
                return;
            } catch (StaleElementReferenceException e) {
                element.clear();
                element.sendKeys(text);
                System.out.println("Typed " + text + " into " + log + " field");
            }
        }
    }

    public void scrollToElement(WebElement element, String text, String log) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(" + text + "));"));
            System.out.println("Scrolled to the " + log + " element");
        } catch (Exception e) {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
            System.out.println("Scrolled to the " + log + " element");
        }
    }

    public void selectFromDropdown(WebElement element, String text, String log) {
        for (int i = 0; i < 2; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                Select select = new Select(element);
                select.selectByVisibleText(text);
                System.out.println("Selected " + log + " from dropdown menu");
                return;
            } catch (StaleElementReferenceException e) {
                Select select = new Select(element);
                select.selectByVisibleText(text);
                System.out.println("Selected " + log + " from dropdown menu");
            }
        }
    }

    public String getText(WebElement element, String log) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("Get text from " + log);
            return element.getText();
        } catch (StaleElementReferenceException e) {
            System.out.println("Get text from " + log);
            return element.getText();
        }
    }

    public void takeScreenshot(String fileName,WebElement element) throws IOException {
        File file = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/screenshots/"+fileName+".png"));
    }
}
