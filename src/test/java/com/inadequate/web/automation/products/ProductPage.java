package com.inadequate.web.automation.products;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProductPage {
    private final String productId;

    public void navigateTo() {
        open("/product/" + productId + "/");
    }

    public ProductPage(String productId) {
        this.productId = productId;
    }

    public SelenideElement getProductName() {
        return $("[data-selenium=product_name]");
    }
}
