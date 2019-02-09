package com.inadequate.web.automation.products;

import com.codeborne.selenide.SelenideElement;
import com.inadequate.web.automation.common.LandingPage;
import com.inadequate.web.automation.common.SportmasterTest;
import com.inadequate.web.automation.search.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;


public class BauerTest extends SportmasterTest {
    private final static String PRODUCT_ID = "10301784";
    private final static String PRODUCT_NAME = "Коньки хоккейные Bauer BTH18 SUPREME 2S";

    private final static String CATALOG_CATEGORY = "Ледовые коньки и хоккей";
    private final static String CATALOG_SUBMENU_TITLE = "МУЖСКИЕ";
    private final static String CATALOG_SUBMENU_ITEM = "Хоккей";

    @Test
    public void testDirectLink() {
        ProductPage productPage = open(ProductPage.getProductUrl(PRODUCT_ID), ProductPage.class);
        productPage.getProductName().shouldHave(text(PRODUCT_NAME));
    }

    @Test
    public void testSearch() {
        LandingPage landingPage = open("/", LandingPage.class);
        landingPage.searchFor(PRODUCT_NAME);

        // product page should be opened right away if there was a single match
        ProductPage productPage = new ProductPage();
        productPage.getProductName().shouldHave(text(PRODUCT_NAME));
    }

    @Test
    public void testCatalogNavigation() {
        LandingPage landingPage = open("/", LandingPage.class);
        SearchResultsPage searchResultsPage = landingPage.openCatalog()
            .expandCatalogCategory(CATALOG_CATEGORY)
            .selectCatalogItem(CATALOG_SUBMENU_TITLE, CATALOG_SUBMENU_ITEM);

        SelenideElement productLink = searchResultsPage.findProductByName(PRODUCT_NAME);
        Assert.assertNotNull(productLink);

        productLink.click();
        ProductPage productPage = new ProductPage();
        productPage.getProductName().shouldHave(text(PRODUCT_NAME));
    }
}