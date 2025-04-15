package Listeners;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;

/**
 * ITestListener interface implements TestNg listeners
 */

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    /**
     * onTestFailure metoda prati test koji je pokrenut, i ako padne poziva metodu takeScreenshot koja pravi
     * screenshot celog ekrana.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            takeScreenshot(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    /**
     * takeScreenshot metoda pravi screenshot celog ekrana (ako folder ne postoji, objekat directory File
     * klase kreira novi folder (directory.mkdirs();)).
     * takeScreenshot metoda kreira ime fajla (String screenshotName) i kreira
     * folder - (failedScreenshots) ukoliko ne postoji (directory.mkdirs();). Kreira lokaciju fajla gde ce biti smesten i
     * nakon toga upisuje screenshot u PNG formatu na lokaciji koju smo mu odredili (String location).
     * @param result
     * Ova metoda se poziva kada test padne (poziva je onTestFailure u listener klasi).
     * Dobija ITestResult objekat koji sadrži informacije o testu koji je pao (ime testa, klasa, greška itd).
     */
    public void takeScreenshot(ITestResult result) throws IOException {
        /**
         * Dobijamo instancu test klase u kojoj se desio pad.
         Tu instancu koristimo da pronađemo polje driver refleksijom (reflection).
         */
        Object testClassInstance = result.getInstance();
        /**
         Ovde pripremamo promenljivu za driver koji ćemo "izvući" refleksijom.
         */
        AppiumDriver driver = null;

        try {
            // Krećemo od klase u kojoj je test  Traverse class hierarchy to find 'driver'
            Class<?> currentClass = testClassInstance.getClass();
            /**
             * Petljamo kroz klasu i njene roditelje (superklase) da pronađemo driver
             */
            while (currentClass != null) {
                /**
                 * Pokušavamo da uzmemo polje sa imenom "driver" iz trenutne klase.
                 * Ako ga nema — uhvatiće se greška i ići ćemo na roditeljsku klasu.
                 */
                try {
                    java.lang.reflect.Field driverField = currentClass.getDeclaredField("driver");
                    /**
                     * Čak i ako je driver deklarisan kao private ili protected, ovo omogućava pristup tom polju.
                     */
                    driverField.setAccessible(true);
                    driver = (AppiumDriver) driverField.get(testClassInstance);
                    break;
                    /**
                     * Ako nađemo polje, uzimamo vrednost tog driver objekta i cast-ujemo ga u AppiumDriver.
                     * Zatim prekidamo petlju — našli smo šta smo tražili.
                     */
                } catch (NoSuchFieldException e) {
                    currentClass = currentClass.getSuperclass(); // go up the chain
                }
            }
            /**
             * Ako ni nakon obilaska svih klasa nismo našli driver, ispisujemo upozorenje i izlazimo iz metode.
             */
            if (driver == null) {
                System.out.println("Could not find driver field in class hierarchy.");
                return;
            }
            /**
             * Koristimo Appium driver da napravimo screenshot — samo prikaz trenutnog web-view-a ili aplikacije, ne hvata status bar, navigaciju itd.
             */
            File file = driver.getScreenshotAs(OutputType.FILE);
            /**
             * Kao naziv fajla koristimo ime test metode koja je pala.
             */
            String screenshotName = "Failed - " + result.getName();
            /**
             * Ako folder src/failedScreenshots ne postoji — kreiramo ga.
             */
            File directory = new File("src/failedScreenshots");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            /**
             * Kopiramo screenshot u pomenuti folder sa željenim imenom.
             */
            FileUtils.copyFile(file, new File(directory, screenshotName + ".png"));
            System.out.println("Screenshot saved for failed test: " + screenshotName);
            /**
             * Ispisujemo potvrdu u konzoli da je screenshot uspešno sačuvan.
             */
        } catch (Exception e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
        }
    }

}