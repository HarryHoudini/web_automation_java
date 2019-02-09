package com.inadequate.web.automation.products;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    public static String getProductUrl(String productId) {
        return "/product/" + productId + "/";
    }

    public SelenideElement getProductName() {
        return $("[data-selenium=product_name]");
    }
}
