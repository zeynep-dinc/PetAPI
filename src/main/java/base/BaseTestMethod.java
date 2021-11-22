package base;


import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import petAPI.NewTextAttribute;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class BaseTestMethod {

    private String url = Driver.readToDriverProperties("url");


    private WebDriver driver;
    @BeforeClass
    public void beforeTest() {
        driver=Driver.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
    }

    @AfterClass
    public void afterTest(Scenario scenario){
        try {
            if(scenario.getStatus()!= Status.PASSED){
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                NewTextAttribute newTextAttribute=new NewTextAttribute();
                FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/fail_scenario_screenshots/"+driver.getTitle().toLowerCase().replaceAll(" ","_")+"_"+newTextAttribute.myDateFormat_ +".png"), true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            driver=Driver.closeDriver();
        }
    }
}
