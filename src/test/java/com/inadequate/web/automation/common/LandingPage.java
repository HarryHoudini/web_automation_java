package com.inadequate.web.automation.common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.inadequate.web.automation.search.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LandingPage {
    @FindBy(how = How.CSS, using = "[data-selenium=product_search_input]")
    private SelenideElement searchBar;

    @FindBy(how = How.ID, using = "newMenu")
    private SelenideElement catalog;

    @FindBy(how = How.CSS, using = "[data-selenium=menu_catalog_dropdown]")
    private SelenideElement catalogDropdown;

    private SelenideElement getCatalogItem(String submenuTitle, String item) {
        return $(By.xpath("//a[contains(@class,'newSubmenuList__link') and contains(text(),'" +
                submenuTitle + "')]/../following-sibling::ul[1]/li/a[contains(text(),'" + item + "')]"));
    }

    private SelenideElement getCatalogCategory(String catalogCategory) {
        return $(By.xpath("//span[@data-selenium='menu_catalog_itemTitle' and contains(text(),'" + catalogCategory + "')]"));
    }

    public LandingPage openCatalog() {
        // TODO: find a good way to handle late event bindings to catalog menu
        Selenide.sleep(20000);
        if (this.catalogDropdown.is(Condition.hidden)) {
            this.catalog.hover();
        }
        return this;
    }

    public LandingPage expandCatalogCategory(String catalogCategory) {
        getCatalogCategory(catalogCategory).hover();
        return this;
    }

    public SearchResultsPage selectCatalogItem(String catalogSubmenuTitle, String catalogSubmenuItem) {
        getCatalogItem(catalogSubmenuTitle, catalogSubmenuItem).click();
        return page(SearchResultsPage.class);
    }

    public SearchResultsPage searchFor(String productName) {
        searchBar.setValue(productName).pressEnter();
        return page(SearchResultsPage.class);
    }
}
