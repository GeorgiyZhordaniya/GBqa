package org.example.task6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {
    private WebDriver driver;

    @FindBy (xpath = ".//a[@title='Избранное']")
    private WebElement favButton;

    @FindBy (css = ".entryunit:nth-child(1)")
    private WebElement controlFav;

    @FindBy (id = "link.id")
    private WebElement favPopUp;

    @FindBy (xpath = ".//button[contains(text(),'Удалить')]")
    private WebElement deleteButton;

    public UserPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UserPage clickFav() {
        favButton.click();
        return this;
    }

    public UserPage mouseOverControlFav() {
        Actions builder = new Actions(driver);
        builder.moveToElement(controlFav).perform();
        return this;
    }

    public UserPage clickControlFav() {
        favPopUp.click();
        return this;
    }

    public WebElement getDeleteButton() {
        return deleteButton;
    }
}
