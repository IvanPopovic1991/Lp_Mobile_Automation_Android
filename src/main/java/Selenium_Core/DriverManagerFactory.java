package Selenium_Core;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(String browser) {

        DriverManager driverManager;
        switch (browser) {
            case "Chrome": {
                driverManager = new ChromeDriverManager();
            }
            break;
            case "Firefox": {
                driverManager = new FirefoxDriverManager();
            }
            break;
            case "Edge": {
                driverManager = new EdgeDriverManager();
            }
            break;
            case "Opera": {
                driverManager = new OperaDriverManager();
            }
            break;
            default: {
                driverManager = new ChromeDriverManager();
            }
        }
        return driverManager;
    }
}
