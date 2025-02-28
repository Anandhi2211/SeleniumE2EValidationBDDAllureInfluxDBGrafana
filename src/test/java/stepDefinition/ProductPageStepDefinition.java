package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webTesting.pages.CartPage;
import org.webTesting.pages.ProductPage;
import org.webTesting.pages.components.ProductComponents;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPageStepDefinition {

    WebDriver driver = Hooks.getDriver();
    ProductPage productPage = new ProductPage(driver);
    CartPage cartPage = new CartPage(driver);
    List<String> productNameToAddList = new ArrayList<>();
    List<String> productsInTheCart = new ArrayList<>();
    int count;
    @And("Verify if all product images are loaded")
    public void verifyIfAllProductImagesAreLoaded() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.productPage.getProductList()
                .forEach(s->
                        {
                            wait.until(ExpectedConditions.visibilityOf(s.getProductImage()));
                            Assert.assertTrue("Image Not Loaded",s.getProductImage().isDisplayed());}
                );
    }

    @And("Verify if all product names are loaded")
    public void verifyIfAllProductNamesAreLoaded() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.productPage.getProductList()
                .forEach(s->
                        {
                            wait.until(ExpectedConditions.visibilityOf(s.getProductName()));
                            Assert.assertTrue("ProductNames Not Loaded",s.getProductName().isDisplayed());}
                );
    }

    @And("Verify if all product prices are loaded")
    public void verifyIfAllProductPricesAreLoaded() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.productPage.getProductList()
                .forEach(s->
                        {
                            wait.until(ExpectedConditions.visibilityOf(s.getProductPrice()));
                            Assert.assertTrue("Product Prices Not Loaded",s.getProductPrice().isDisplayed());}
                );
    }
    @And("Verify if all product addToCarts are loaded")
    public void verifyIfAllProductAddToCartsAreLoaded() {
        System.out.println("ImHere");
        ProductPage productPage = new ProductPage(this.driver);
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        productPage.getProductList()
                .forEach(s->
                        {
                            wait.until(ExpectedConditions.visibilityOf(s.getProductName()));
                            Assert.assertTrue("add to cart is not displayed ",(s.getAddToCart().isDisplayed()&&s.getAddToCart().isEnabled()));
//                            Assert.assertTrue("button is not clickable", );
                        }
                );
    }

    @And("Click add to cart")
    public void clickAddToCart() {
        System.out.println("click add to cart");
        
    }

    @Then("Verify if cart icon properly updates the count")
    public void verifyIfCartIconProperlyUpdatesTheCount() {
        System.out.println("chk for icon count");
        boolean result = (count == Integer.parseInt(this.productPage.getCartItemCount().getText()));
        Assert.assertTrue("Count is not equal",result);
    }

    @And("Click cart icon")
    public void clickCartIcon() {
        this.productPage.getCartButton().click();
        System.out.println("click cart icon");
    }

    @Then("Verify if all the products added")
    public void verifyIfAllTheProductsAdded() {
        System.out.println("products added");
        this.productsInTheCart = cartPage.getProductList().stream().map(s->s.getProductName().getText()).collect(Collectors.toList());
        Assert.assertTrue("Not Present",this.productNameToAddList.containsAll(productsInTheCart));
    }
    @When("Choose the following products:")
    public void choose_the_following_products(DataTable dataTable) {
        this.productNameToAddList = dataTable.asList(String.class);
        this.productPage.getProductList().stream()
                .filter(s-> this.productNameToAddList.stream()
                        .anyMatch(prd->s.getProductName().getText().contains(prd)))
                .forEach(prd->
                {prd.getAddToCart().click();
                    count++;
                }
                );
    }

    @Then("Verify all the products name and price")
    public void verifyAllTheProductsNameAndPrice() {
        this.cartPage.getProductList()
                .stream().filter(s -> this.productNameToAddList.stream()
                        .anyMatch(p->s.getProductName().getText().contains(p)))
                .forEach(prd->{
                    Assert.assertTrue("Product Name is not matching",this.productNameToAddList.contains(prd));
                });
    }

    @And("Verify the total amount")
    public void verifyTheTotalAmount() {
        System.out.println("Verify the total amount");
    }

    @And("Verify the checkout button is present")
    public void verifyTheCheckoutButtonIsPresent() {
        this.cartPage.getCheckOutButton().click();
    }
}
