package base;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class BaseTestFunctions {

    private final WebDriver driver = Driver.getDriver();
    private final Logger log = LogManager.getLogger(BaseTestFunctions.class);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions actions=new Actions(driver);
    private WebElement optionValueTextElement;

    protected String getUserName=Driver.readToDriverProperties("userName");
    protected String getPassword=Driver.readToDriverProperties("password");

    public void waitFor(long second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void actionSendKeys(Keys keys){
        actions.sendKeys(keys).build().perform();
        waitFor(1);
    }

    public void waitForPageLoad(Long second){
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(second));
        }
        catch (Exception exception){
            exception.getMessage();
        }
    }
    public void assertWebTitle(String title) {
        try {
            waitFor(10);
            System.out.println("\nOrjinal title: " + driver.getTitle() + "\n beklenen title: " + title);
            Assert.assertTrue(driver.getTitle().contains(title));
            log.info("There is text contains in a title. The driver title: "+driver.getTitle());
        } catch (Exception exception) {
            exception.getMessage();
            System.out.println("The driver title: " + driver.getTitle());
            log.error("There is a problem in title verify.;\n" + exception.getMessage());
        }

    }

    public void elementToBeClickable(WebElement clickElement) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickElement));
            clickElement.click();
            System.out.println("The " + clickElement + " is clicked!!");
            log.info("The " + clickElement + " is clicked!!");
        } catch (TimeoutException timeoutException) {
            System.out.println("The " + clickElement + " is timeout for 10 seconds.");
            timeoutException.getMessage();
            log.error("The " + clickElement + " is timeout for 10 seconds.");
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("The " + clickElement + " isn't such element.");
            noSuchElementException.getMessage();
            log.error("The " + clickElement + " isn't such element.");
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            System.out.println("The " + clickElement + " isn't clickable!");
            elementClickInterceptedException.getMessage();
            log.error("The " + clickElement + " isn't clickable!");
        } catch (Exception exception) {
            exception.getMessage();
            log.error("There is an exception:\n" + exception.getMessage());
        }
    }

    public void isDisplay(WebElement element) {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
            if (element.isDisplayed()) {
                log.info("The element(" + element + ") is displayed.");
                System.out.println("The element(" + element + ") is displayed.");
            }
        } catch (Exception exception) {
            exception.getMessage();
            log.error("The element(" + element + ") isn't display!");
            System.out.println("The element(" + element + ") isn't display!");
        }
    }

    public void sendKeysFunction(WebElement sendKeysElement, String value) {
        try {
            elementToBeClickable(sendKeysElement);
            wait.until(ExpectedConditions.visibilityOf(sendKeysElement));
            sendKeysElement.clear();
            sendKeysElement.sendKeys(value);
            System.out.println("The " + sendKeysElement + " is in the value " + value + " wrote!!");
            log.info("The " + sendKeysElement + " is in the value " + value + " wrote!!");
        } catch (TimeoutException timeoutException) {
            System.out.println("The " + sendKeysElement + " is timeout for 10 seconds.");
            timeoutException.getMessage();
            log.error("The " + sendKeysElement + " is timeout for 10 seconds.");
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("The " + sendKeysElement + " isn't such element.");
            noSuchElementException.getMessage();
            log.error("The " + sendKeysElement + " isn't such element.");
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            System.out.println("The " + sendKeysElement + " isn't clickable!");
            elementClickInterceptedException.getMessage();
            log.error("The " + sendKeysElement + " isn't clickable!");
        } catch (Exception exception) {
            exception.getMessage();
            log.error("There is an exception:\n" + exception.getMessage());
        }
    }

    public void tabiDegistir() throws InterruptedException {
        try {
            System.out.println(driver.getTitle() + " old page title");
            Thread.sleep(1000);
            Set<String> tab_handles = driver.getWindowHandles();
            int number_of_tabs = tab_handles.size();
            int new_tab_index = number_of_tabs - 1;
            driver.close();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
            driver.switchTo().window(tab_handles.toArray()[new_tab_index].toString());
            Thread.sleep(1000);
            System.out.println(driver.getTitle() + " current page title.");
            log.info("Switch To driver tab!!");
        } catch (TimeoutException timeoutException) {
            System.out.print("The " + driver.getTitle() + " is timeout for 10 seconds.");
            timeoutException.getMessage();
            log.error("The " + driver.getTitle() + " is timeout for 10 seconds.");
        } catch (Exception exception) {
            exception.getMessage();
            log.error("There is an exception:\n" + exception.getMessage());
        }
    }

    public void chooseToSelectBox(WebElement selectBoxElement,String optionValue){
        try {
            elementToBeClickable(selectBoxElement);
            optionValueTextElement=driver.findElement(By.xpath("//option[contains(text(),'"+optionValue+"')]"));
            elementToBeClickable(optionValueTextElement);
            log.info("The "+selectBoxElement+" select box in "+optionValue+" option value selected.");
        } catch (TimeoutException timeoutException) {
            System.out.println("The " + selectBoxElement + "or "+optionValue+" is timeout for 10 seconds.");
            timeoutException.getMessage();
            log.error("The " +  selectBoxElement + "or "+optionValue+" is timeout for 10 seconds.");
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("The " + selectBoxElement + "or "+optionValue + " isn't such element.");
            noSuchElementException.getMessage();
            log.error("The " + selectBoxElement + "or "+optionValue + " isn't such element.");
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            System.out.println("The " + selectBoxElement + "or "+optionValue + " isn't clickable!");
            elementClickInterceptedException.getMessage();
            log.error("The " + selectBoxElement + "or "+optionValue + " isn't clickable!");
        } catch (Exception exception) {
            exception.getMessage();
            log.error("There is an exception:\n" + exception.getMessage());
        }
    }
}
