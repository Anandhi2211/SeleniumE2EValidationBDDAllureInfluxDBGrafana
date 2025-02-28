package org.webTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.webTesting.pages.components.ProductComponents;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    @FindBy(css = ".cart_item")
    private List<WebElement> productList;
    private WebDriver driver;
    @FindBy(css=".btn_action.checkout_button")
    private WebElement checkOutButton;
    public CartPage(WebDriver driver) {
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

    public WebElement getCheckOutButton() {
        return checkOutButton;
    }
}
