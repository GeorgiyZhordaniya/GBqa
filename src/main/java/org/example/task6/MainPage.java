package org.example.task6;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MainPage {

    private WebDriver driver;

    @FindBy (css = ".s-header-item__link--login")
    private WebElement loginButton;

    @FindBy (id = "user")
    private WebElement loginField;

    @FindBy (id = "lj_loginwidget_password")
    private WebElement passwordField;

    @FindBy (name = "action:login")
    private WebElement enterButton;

    @FindBy (xpath = "//span[contains(.,'Имя не найдено')]")
    private List<WebElement> nameError;

    @FindBy (xpath = "//span[contains(.,'Неверный пароль')]")
    private List<WebElement> passwordError;

    @FindBy (css = ".post-card--big > .post-card__link")
    private WebElement postCard;

    @FindBy (css = ".s-header-item__link--user")
    private WebElement userPage;

    @FindBy (css = ".flaticon--search")
    private List<WebElement> searchButton;

    @FindBy (css = ".s-inline-search-input")
    private WebElement searchField;

    @FindBy (xpath = "//a[contains(text(),'Новые лица')]")
    private WebElement firstCategory; //категория "Новые лица"

    @FindBy (css = ".s-header-item__link--user")
    private WebElement userButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage clickLogin() {
        loginButton.click();
        return this;
    }

    public MainPage enterLogin(String login) {
        loginField.sendKeys(login);
        return this;
    }

    public MainPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public MainPage enterSite() {
        enterButton.click();
        return this;
    }

    public void clickPost() {
        try {
            Thread.sleep(3000);  //необходимо для прогрузки страницы
        } catch (InterruptedException e) {}
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", postCard);
        postCard.click();
    }

    public Boolean noError() {
        try {
            Thread.sleep(1000);  //необходимо для прогрузки ошибки
        } catch (InterruptedException e) {}
        return (driver.findElements(By.xpath("//span[contains(.,'Неверный пароль')]")).size() == 0
                || driver.findElements(By.xpath("//span[contains(.,'Имя не найдено')]")).size() == 0);
    }

    public MainPage clickSearch() {
        searchButton.get(1).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        return this;
    }

    public SearchPage searchTest() {
        searchField.sendKeys("Test", Keys.ENTER);

        try {
            Thread.sleep(5000);  //необходимо для прогрузки страницы
        } catch (InterruptedException e) {}

        List<String> windowHandles = new ArrayList(driver.getWindowHandles());
        String secondTab = windowHandles.get(1);

        driver.switchTo().window(secondTab);

        return new SearchPage(driver);
    }

    public CategoryPage clickCategory() {
        firstCategory.click();
        try {
            Thread.sleep(3000);  //необходимо для прогрузки страницы
        } catch (InterruptedException e) {}
        return new CategoryPage(driver);
    }

    public UserPage clickUser() {
        userButton.click();
        return new UserPage(driver);
    }

}
