package org.example.task6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage {
    private WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
