package awesomecucumber.stepdefinitions;

import awesomecucumber.context.TestContext;
import awesomecucumber.pages.CartPage;
import awesomecucumber.pages.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutStepDefinitions {

    private final WebDriver driver;
    private final TestContext context;

    public CheckoutStepDefinitions(TestContext context){
        this.context = context;
        driver = context.driver;
    }

    @And("I'm on the Checkout page")
    public void iMOnTheCheckoutPage() {
        new CartPage(driver).checkout();
    }

    @When("I provide billing details")
    public void iProvideBillingDetails() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.setBillingDetails(context.billingDetails);
    }

    @And("I place an order")
    public void iPlaceAnOrder() {
        new CheckoutPage(driver).placeOrder();
    }

    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() {
        Assert.assertEquals(new CheckoutPage(driver).getNotice(),
                "Thank you. Your order has been received.");
    }
}
