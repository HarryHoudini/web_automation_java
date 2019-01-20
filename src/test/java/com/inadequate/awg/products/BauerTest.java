package com.inadequate.awg.products;

import com.codeborne.selenide.SelenideElement;
import com.inadequate.awg.common.LandingPage;
import com.inadequate.awg.common.SportmasterTest;
import com.inadequate.awg.search.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;


public class BauerTest extends SportmasterTest {
    private final String PRODUCT_ID = "10301784";
    private final String PRODUCT_NAME = "Коньки хоккейные Bauer BTH18 SUPREME 2S";

    private final String CATALOG_CATEGORY = "Ледовые коньки и хоккей";
    private final String CATALOG_SUBMENU_TITLE = "МУЖСКИЕ";
    private final String CATALOG_SUBMENU_ITEM = "Хоккей";

    @Test
    public void testDirectLink() {
        ProductPage productPage = new ProductPage(PRODUCT_ID);
        productPage.navigateTo();
        productPage.getProductName().shouldHave(text(PRODUCT_NAME));
    }

    @Test
    public void testSearch() {
        LandingPage landingPage = new LandingPage();
        landingPage.navigateTo();
        landingPage.getSearchBar().setValue(PRODUCT_NAME).pressEnter();

        ProductPage productPage = new ProductPage(PRODUCT_ID);
        productPage.getProductName().shouldHave(text(PRODUCT_NAME));
    }

    @Test
    public void testCatalogNavigation() {
        LandingPage landingPage = new LandingPage();
        landingPage.navigateTo();
        landingPage.getCatalog().hover();
        landingPage.getCatalogCategory(CATALOG_CATEGORY).shouldBe(visible).hover();
        landingPage.getCatalogItem(CATALOG_SUBMENU_TITLE, CATALOG_SUBMENU_ITEM).shouldBe(visible).click();

        SearchResultsPage searchResultsPage = new SearchResultsPage();

        SelenideElement productLink = searchResultsPage.findProductByName(PRODUCT_NAME);
        Assert.assertNotNull(productLink);

        productLink.click();
        ProductPage productPage = new ProductPage(PRODUCT_ID);
        productPage.getProductName().shouldHave(text(PRODUCT_NAME));
    }
}