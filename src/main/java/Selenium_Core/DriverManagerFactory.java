package Selenium_Core;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(String browser) {

        DriverManager driverManager;
        switch (browser) {
            case "chrome": {
                driverManager = new ChromeDriverManager();
            }
            break;
            case "firefox": {
                driverManager = new FirefoxDriverManager();
            }
            break;
            case "edge": {
                driverManager = new EdgeDriverManager();
            }
            break;
            case "opera": {
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
