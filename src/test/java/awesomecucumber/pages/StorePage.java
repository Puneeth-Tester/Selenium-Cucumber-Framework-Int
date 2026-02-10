package awesomecucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.charset.StandardCharsets;

public class StorePage extends BasePage {

    public StorePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;

    public void addToCart(String productName) {

        // Using the below code while running through JUnit runner
        /*String raw = "a[aria-label='Add “" + productName + "” to your cart']";
        byte[] bytes = raw.getBytes(StandardCharsets.UTF_8);
        String utf8Encoded = new String(bytes, StandardCharsets.UTF_8);
        By addToCartBtn = By.cssSelector(utf8Encoded);*/

        By addToCartBtn = By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
    }
}
