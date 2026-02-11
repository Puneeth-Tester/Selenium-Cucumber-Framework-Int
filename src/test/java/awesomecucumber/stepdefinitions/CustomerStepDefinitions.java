package awesomecucumber.stepdefinitions;

import awesomecucumber.constants.MyConstants;
import awesomecucumber.context.TestContext;
import awesomecucumber.domainobjects.BillingDetails;
import awesomecucumber.pages.StorePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class CustomerStepDefinitions {

    private final WebDriver driver;
    private final TestContext context;

    public CustomerStepDefinitions(TestContext context){
        this.context = context;
        this.driver = context.driver;
    }

    @Given("I'm a guest customer")
    public void iMAGuestCustomer() {
        new StorePage(driver).load(MyConstants.STORE);
    }
    @And("my billing details are")
    public void myBillingDetailsAre(BillingDetails billingDetails) {
        context.billingDetails = billingDetails;
    }

}
