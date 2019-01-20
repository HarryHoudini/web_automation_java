package com.inadequate.awg.common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LandingPage {
    public void navigateTo() {
        open("/");
    }

    public SelenideElement getSearchBar() {
        return $("[data-selenium=product_search_input]");
    }

    private SelenideElement getCatalog() {
        return $("#newMenu");
    }

    public SelenideElement getCatalogItem(String submenuTitle, String item) {
        return $(By.xpath("//a[contains(@class,'newSubmenuList__link') and contains(text(),'" +
                submenuTitle + "')]/../following-sibling::ul[1]/li/a[contains(text(),'" + item + "')]"));
    }

    public SelenideElement getCatalogCategory(String catalogCategory) {
        return $(By.xpath("//span[@data-selenium='menu_catalog_itemTitle' and contains(text(),'" + catalogCategory + "')]"));
    }

    public void openCatalog() {
        // TODO: find a good way to handle late event bindings to catalog menu
        Selenide.sleep(20000);
        if (this.getCatalogDropdown().is(Condition.hidden)) {
            this.getCatalog().hover();
        }
    }

    private SelenideElement getCatalogDropdown() {
        return $("[data-selenium=menu_catalog_dropdown]");
    }
}
