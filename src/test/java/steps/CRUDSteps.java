package steps;

import base.BaseTestMethod;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;
import petAPI.CRUD;

public class CRUDSteps extends BaseTestMethod {
    Scenario scenario;
    CRUD crud=new CRUD();

    @Before
    public void beforeTest(Scenario scenario){
        super.beforeTest();
        this.scenario = scenario;
        crud.actionSendKeys(Keys.PAGE_DOWN);
        crud.actionSendKeys(Keys.PAGE_DOWN);
    }

    @Given("{string} open to form")
    public void open_to_form(String tabName) {
        crud.tabClick(tabName);
        crud.actionSendKeys(Keys.ARROW_DOWN);
    }

    @Then("{string} close to form")
    public void close_to_form(String tabName) {
        crud.actionSendKeys(Keys.PAGE_UP);
        crud.actionSendKeys(Keys.PAGE_UP);
        crud.tabClick(tabName);
    }

    @Then("click to {string} button")
    public void click_to_button(String buttonText) {
        crud.buttonClickElement(buttonText);
    }
    @Then("complate to textarea with data")
    public void complate_to_textarea_with_data() {
        crud.complateToTextArea();
    }
    @Then("read to code {string}")
    public void read_to_code(String request) {
        crud.readToCode(request);
    }
    @Then("enter to {string} is {string}")
    public void enter_to_is(String path,String text) {
        crud.enterToIs(path,text);
    }

    @Then("open to url")
    public void open_to_url() throws InterruptedException {
        crud.openToUrl();
    }
    @After
    public void afterTest(){
        super.afterTest(scenario);
    }
}
