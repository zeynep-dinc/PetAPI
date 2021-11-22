package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Driver {

    private static final Logger logger = LogManager.getLogger(Driver.class);
    private static WebDriver driver;

    static Properties prop = new Properties();

    protected static String readToDriverProperties(String variable) {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(input);
            System.out.println(prop.getProperty("driver." + variable));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            return prop.getProperty("driver." + variable);
        }
    }

    public static WebDriver getDriver() {
        try {
            if (driver == null) {
                String driverType = readToDriverProperties("aktiveDriver");
                switch (driverType) {
                    case "firefox":
                        System.setProperty(readToDriverProperties("keyForFirefox"), readToDriverProperties("firefoxdriver"));
                        driver = new FirefoxDriver();
                        break;
                    default:
                        System.setProperty(readToDriverProperties("keyForChrome"), readToDriverProperties("chromedriver"));
                        driver = new ChromeDriver();
                }
                logger.info("-------------Driver is starting-------------");
            }
        } catch (Exception ex) {
            ex.getMessage();
            logger.error(ex.getMessage());
        }
        return driver;
    }


    public static WebDriver closeDriver() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
                logger.info("-------------Driver clossed.-------------");
            }
        } catch (Exception ex) {
            ex.getMessage();
            logger.error(ex.getMessage());
        }
        return null;
    }
}
