package org.example.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    public static void login(WebDriver driver) {
        WebElement loginButton = driver.findElement(By.cssSelector(".s-header-item__link--login"));
        loginButton.click();

        WebElement loginField = driver.findElement(By.id("user"));
        WebElement passwordField = driver.findElement(By.id("lj_loginwidget_password"));
        loginField.sendKeys("test01234");
        passwordField.sendKeys("Test123");

        WebElement login = driver.findElement(By.name("action:login"));
        login.click();
    }
}
