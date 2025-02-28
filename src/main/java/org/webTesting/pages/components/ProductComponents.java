package org.webTesting.pages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Component Page
 * Doesn't need Page InitFactory
 */
public class ProductComponents {
    @FindBy(xpath = ".//img[@class='inventory_item_img']")
    private WebElement productImage;
    @FindBy(css = ".inventory_item_name")
    private WebElement productName;
    @FindBy(css = ".inventory_item_price")
    private WebElement productPrice;

    public WebElement getAddToCart() {
        return addToCart;
    }

    @FindBy(css = ".btn_primary.btn_inventory")
    private WebElement addToCart;

    public ProductComponents(WebElement productList) {
        PageFactory.initElements(productList,this);
    }

    public WebElement getProductImage() {
        return productImage;
    }
    public WebElement getProductName() {
        return productName;
    }
    public WebElement getProductPrice() {
        return productPrice;
    }
}
