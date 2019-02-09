package com.inadequate.web.automation.search;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.text;

public class SearchResultsPage {
    @FindBy(how = How.CSS, using = ".sm-category__main-sorting_top-page-wrap span.active")
    private SelenideElement activePage;

    @FindBy(how = How.CSS, using = "a[rel=next]")
    private SelenideElement nextPage;

    @FindBy(how = How.CSS, using = "[data-selenium=product_link]")
    private ElementsCollection productNames;

    public SelenideElement findProductByName(String productName) {
        String currentPage = "1";
        if (this.activePage.exists()) {
            currentPage = this.activePage.text();
        }
        SelenideElement productLink = this.productNames.findBy(text(productName));

        if (!productLink.exists()) {
            if (this.nextPage.exists()) {
                this.nextPage.click();
                this.activePage.shouldNotHave(text(currentPage));
                return findProductByName(productName);
            } else {
                return null;
            }
        }

        return productLink;
    }
}
