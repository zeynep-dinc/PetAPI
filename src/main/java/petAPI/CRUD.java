package petAPI;

import base.BaseTestFunctions;
import base.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

public class CRUD extends BaseTestFunctions {
    WebDriver driver;

    private String statusCode;
    private WebElement tabElement,buttonElement,textElement;
    private String BASE_URL="https://petstore.swagger.io/v2/pet/1";

    NewTextAttribute newTextAttribute=new NewTextAttribute();

    public CRUD(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//textarea[@class='body-param__text']")
    private WebElement newIdTextArea;

    @FindBy(xpath = "(//td[@class='response-col_status'])[1]")
    private WebElement firstResponceCode;


    public void tabClick(String tabName){
        tabElement=driver.findElement(By.xpath("//div[contains(text(),'"+tabName+"')]"));
        elementToBeClickable(tabElement);
        waitFor(5);
    }

    public void buttonClickElement(String buttonText){
        buttonElement=driver.findElement(By.xpath("//button[contains(text(),'"+buttonText+"')]"));
        elementToBeClickable(buttonElement);
        actionSendKeys(Keys.PAGE_DOWN);
        waitFor(5);
    }

    public void complateToTextArea(){
        sendKeysFunction(newIdTextArea,newTextAttribute.NEW_ID);
        actionSendKeys(Keys.PAGE_DOWN);
    }

    public void readToCode(String request){
        statusCode=firstResponceCode.getText();
        System.out.println(request+"= "+statusCode);
        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/ResponseCodes.txt",true))){
            bufferedWriter.write("\n"+request+": "+statusCode+"\n"+ newTextAttribute.standartDateFormat+"\n");
        } catch (IOException  e) {
            System.out.println("Unable to write file ");
            e.printStackTrace();
        }
    }


    public void enterToIs(String path, String text){
        textElement=driver.findElement(By.xpath("//tbody/tr["+path+"]/td[2]/input[1]"));
        sendKeysFunction(textElement,text);
    }

    public void openToUrl() throws InterruptedException {
        driver.navigate().to(BASE_URL);
    }
}
