package com.inadequate.awg.products;

import com.inadequate.awg.common.SportmasterTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;


public class BauerTest extends SportmasterTest {
    private final String PRODUCT_NAME = "Коньки хоккейные Bauer BTH18 SUPREME 2S";

    @Test
    public void testDirectLink() {
        open(baseUrl + "/product/10301784/");
        ProductPage productPage = new ProductPage();
        productPage.getProductName().shouldHave(text(PRODUCT_NAME));
    }
}