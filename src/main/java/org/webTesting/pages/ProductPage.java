package org.webTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.webTesting.pages.components.ProductComponents;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    @FindBy(css = ".fa-layers-counter.shopping_cart_badge")
    private WebElement cartItemCount;
    @FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18.fa-3x")
    private WebElement cartButton;
    @FindBy(css = ".inventory_item")
    private List<WebElement> productList;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
    public List<ProductComponents> getProductList() {
        List<ProductComponents> productComponentsList = new ArrayList<>();
        for(WebElement prod : this.productList){
            productComponentsList.add(new ProductComponents(prod));
        }
        return productComponentsList;
    }
    public WebElement getCartItemCount() {
        return cartItemCount;
    }

    public WebElement getCartButton() {
        return cartButton;
    }
}
