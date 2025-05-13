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
import java.util.Set;
import java.util.List;
import java.util.Objects;

public class BasePage {

    protected AndroidDriver driver;
    WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        String className = this.getClass().getSimpleName();
        System.out.println(className);
        /*if (!className.equals("HomePage") && !className.equals("BasePage")) {
            wait.until(webDriver ->
            {
                String value = webDriver.findElement(By.xpath("//input[@class='lcField FlavorRegistration']")).getAttribute("value");
                return "quick".equals(value) || "hasStages".equals(value);
            });
        }*/
    }

    public void getDriver (String url){
        driver.get(url);
        wait.until(webDriver -> {
            String value = webDriver.findElement(By.xpath("//input[@class='lcField FlavorRegistration']")).getAttribute("value");
            return "quick".equals(value) || "hasStages".equals(value);
        });
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
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(" + text + "));"));
            System.out.println("Scrolled to the " + log + " element");
        }
    }


    public void scrollToElementBy (By elementBy){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        /*js.executeScript("setTimeout(() => arguments[0].scrollIntoView(true), 100);", driver.findElement(elementBy));
        js.executeScript("window.scrollBy(0, -200);");*/

        WebElement element = driver.findElement(elementBy);
        int elementPosition = element.getLocation().getY();

// Calculate the vertical position needed to bring the element to the middle
        int windowHeight = ((Long) js.executeScript("return window.innerHeight")).intValue();
        int scrollToPosition = elementPosition - (windowHeight / 2);

// Scroll to bring the element in the center
        js.executeScript("window.scrollTo(0, " + scrollToPosition + ");");
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

    public String getTextBy(By by, String log) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            System.out.println("Got text from " + log + " element.");
            return driver.findElement(by).getText();
        } catch (StaleElementReferenceException e) {
            System.out.println("Got text from " + log + " element.");
            return driver.findElement(by).getText();
        }
    }

    public void takeScreenshot(String fileName) throws IOException {
        File file = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/screenshots/" + fileName + ".png"));
    }

    public void takeScreenshot(String fileName, WebElement element) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(element));
        File file = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/screenshots/" + fileName + ".png"));
    }

    public void switchToNewWindow() {
        String originalHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public WebElement returnDisplayedElement(By element) {
        int retries = 5;
        int delay = 1000; // 2 seconds

        for (int i = 0; i < retries; i++) {
            try {
                // Check for element visibility
                List<WebElement> elements = driver.findElements(element);
                for (WebElement displayedElement : elements) {
                    if (displayedElement.isDisplayed()) {
                        return displayedElement;
                    }
                }
                // If element not found, pause briefly and try again
                Thread.sleep(delay);
            } catch (Exception e) {
                System.out.println("Retrying to find element: " + e.getMessage());
            }
        }
        return null; // Return null if not found after retries
    }

}
