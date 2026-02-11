package awesomecucumber.stepdefinitions;

import awesomecucumber.context.TestContext;
import awesomecucumber.domainobjects.Product;
import awesomecucumber.pages.CartPage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartStepDefinitions {

    private final WebDriver driver;

    public CartStepDefinitions(TestContext context){
        driver = context.driver;
    }

    @Then("I should see {int} {product} in the cart")
    public void i_should_see_in_the_cart(int quantity, Product product) {
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        Assert.assertEquals(cartPage.getProductQuantity(), quantity);
    }

}
