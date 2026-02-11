package awesomecucumber.stepdefinitions;

import awesomecucumber.apis.CartApi;
import awesomecucumber.constants.EndPoint;
import awesomecucumber.context.TestContext;
import awesomecucumber.domainobjects.Product;
import awesomecucumber.pages.PageFactoryManager;
import awesomecucumber.pages.StorePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class StoreStepDefinitions {

    private final StorePage storePage;
    private final TestContext context;

    public StoreStepDefinitions(TestContext context){
        this.context = context;
        storePage = PageFactoryManager.getStorePage(context.driver);
    }

    @Given("I'm on the Store Page")
    public void i_m_on_the_store_page() {
        storePage.load(EndPoint.STORE.url);
    }

    @When("I add a {product} to the cart")
    public void i_add_a_to_the_cart(Product product) {
        storePage.addToCart(product.getName());
    }

    @And("I have a product in the cart")
    public void iHaveAProductInTheCart() {
        // storePage.addToCart("Blue Shoes");
        CartApi cartApi = new CartApi(context.cookies.getCookies());
        cartApi.addToCart(1215, 1);
        context.cookies.setCookies(cartApi.getCookies());
        context.cookies.injectCookiesToBrowser(context.driver);
    }

}
