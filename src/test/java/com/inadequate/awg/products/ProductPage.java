package com.inadequate.awg.products;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    public SelenideElement getProductName() {
        return $(By.cssSelector("h1[itemprop=name]"));
    }

    public SelenideElement getBuyButton() {
        return $(By.cssSelector("[data-selenium=add_to_basket]"));
    }
}
