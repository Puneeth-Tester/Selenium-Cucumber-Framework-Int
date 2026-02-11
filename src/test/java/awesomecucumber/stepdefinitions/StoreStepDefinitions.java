package awesomecucumber.stepdefinitions;

import awesomecucumber.constants.EndPoint;
import awesomecucumber.context.TestContext;
import awesomecucumber.domainobjects.Product;
import awesomecucumber.pages.StorePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class StoreStepDefinitions {

    private final WebDriver driver;

    public StoreStepDefinitions(TestContext context){
        driver = context.driver;
    }

    @Given("I'm on the Store Page")
    public void i_m_on_the_store_page() {
        new StorePage(driver).load(EndPoint.STORE.url);
    }

    @When("I add a {product} to the cart")
    public void i_add_a_to_the_cart(Product product) {
        new StorePage(driver).addToCart(product.getName());
    }

    @And("I have a product in the cart")
    public void iHaveAProductInTheCart() {
        new StorePage(driver).addToCart("Blue Shoes");
    }

}
