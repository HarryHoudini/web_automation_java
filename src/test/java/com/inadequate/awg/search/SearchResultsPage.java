package com.inadequate.awg.search;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {
    public SelenideElement getActivePage() {
        return $(".sm-category__main-sorting_pager span.active");
    }

    public SelenideElement getNextPage() {
        return $("a[rel=next]");
    }

    public ElementsCollection getProductNames() {
        return $$("[data-selenium=product_link]");
    }

    public SelenideElement findProductByName(String productName) {
        String currentPage = this.getActivePage().text();
        SelenideElement productLink = this.getProductNames().findBy(text(productName));

        if (!productLink.exists()) {
            if (this.getNextPage().exists()) {
                this.getNextPage().click();
                this.getActivePage().shouldNotHave(text(currentPage));
                return findProductByName(productName);
            } else {
                return null;
            }
        }

        return productLink;
    }
}
