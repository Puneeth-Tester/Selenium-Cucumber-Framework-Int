package awesomecucumber;

import awesomecucumber.factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class MyStepDefinitions {

    private WebDriver driver;
    @Given("I'm on the Store Page")
    public void i_m_on_the_store_page() {
        driver = DriverFactory.getDriver();
        driver.get("https://askomdch.com/store");
    }
    @When("I add a {string} to the cart")
    public void i_add_a_to_the_cart(String productName) {
        By addToCartBtn = By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
        driver.findElement(addToCartBtn).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        By viewCartLink = By.cssSelector("a[title='View cart']");
        driver.findElement(viewCartLink).click();
    }
    @Then("I should see {int} {string} in the cart")
    public void i_should_see_in_the_cart(Integer quantity, String productName) {
        By productNameFld = By.cssSelector("td[class='product-name'] a");
        String actualProductName = driver.findElement(productNameFld).getText();
        By productQuantityFld = By.cssSelector("input[type=\"number\"]");
        String actualQuantity = driver.findElement(productQuantityFld).getAttribute("value");
        Assert.assertEquals(actualProductName, productName);
        Assert.assertEquals(actualQuantity, quantity.toString());
    }

    @Given("I'm a guest customer")
    public void iMAGuestCustomer() {
        driver = DriverFactory.getDriver();
        driver.get("https://askomdch.com/store");
    }

    @And("I have a product in the cart")
    public void iHaveAProductInTheCart() {
        By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
        driver.findElement(addToCartBtn).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        By viewCartLink = By.cssSelector("a[title='View cart']");
        driver.findElement(viewCartLink).click();
    }

    @And("I'm on the Checkout page")
    public void iMOnTheCheckoutPage() {
        By proceedToCheckoutBtn = By.cssSelector(".checkout-button");
        driver.findElement(proceedToCheckoutBtn).click();
    }

    @When("I provide billing details")
    public void iProvideBillingDetails(List<Map<String, String>> billingDetails) {
        By billingFirstnameFld = By.id("billing_first_name");
        By billingLastNameFld = By.id("billing_last_name");
        By billingAddressOneFld = By.id("billing_address_1");
        By billingCityFld = By.id("billing_city");
        By billingStateDropDown = By.id("billing_state");
        By billingZipFld = By.id("billing_postcode");
        By billingEmailFld = By.id("billing_email");

        driver.findElement(billingFirstnameFld).clear();
        driver.findElement(billingFirstnameFld).sendKeys(billingDetails.get(0).get("firstname"));

        driver.findElement(billingLastNameFld).clear();
        driver.findElement(billingLastNameFld).sendKeys(billingDetails.get(0).get("lastname"));

        driver.findElement(billingAddressOneFld).clear();
        driver.findElement(billingAddressOneFld).sendKeys(billingDetails.get(0).get("address_line1"));

        driver.findElement(billingCityFld).clear();
        driver.findElement(billingCityFld).sendKeys(billingDetails.get(0).get("city"));

        Select sel = new Select(driver.findElement(billingStateDropDown));
        sel.selectByVisibleText(billingDetails.get(0).get("state"));

        driver.findElement(billingZipFld).clear();
        driver.findElement(billingZipFld).sendKeys(billingDetails.get(0).get("zip"));

        driver.findElement(billingEmailFld).clear();
        driver.findElement(billingEmailFld).sendKeys(billingDetails.get(0).get("email"));
    }

    @And("I place an order")
    public void iPlaceAnOrder() {
        By placeOrderBtn = By.id("place_order");
        driver.findElement(placeOrderBtn).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() {
        By noticeTxt = By.cssSelector(".woocommerce-notice");
        String actualNoticeMsg = driver.findElement(noticeTxt).getText();
        Assert.assertEquals(actualNoticeMsg,
                "Thank you. Your order has been received.");
    }
}
