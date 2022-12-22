package org.example.task6;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PostPage {
    private WebDriver driver;
    @FindBy (xpath = ".//button[@title='Добавить в избранное']")
    private List<WebElement> addFavButton;

    @FindBy (css = ".popup-1-2-32")
    private WebElement addFavPopUp;

    @FindBy (xpath = ".//a[contains(text(), 'Подписаться')]")
    private WebElement subscribeButton;

    @FindBy (xpath = ".//a[contains(text(), 'Вы подписаны')]")
    private List<WebElement> subscribed;

    public PostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PostPage addFav() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addFavButton.get(0));
        addFavButton.get(0).click();
        return this;
    }

    public WebElement addFavPopUp() {
        return addFavPopUp;
    }

    public PostPage clickSubscribe() {
        subscribeButton.click();
        return this;
    }

    public boolean isSubscribed() {
        return (subscribed.size() != 0);
    }
}
