package org.example.task5;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class RemoveFavTest {
    static WebDriver driver;
    @BeforeAll
    static void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.livejournal.com/");

        WebElement loginButton = driver.findElement(By.cssSelector(".s-header-item__link--login"));
        loginButton.click();

        WebElement loginField = driver.findElement(By.id("user"));
        WebElement passwordField = driver.findElement(By.id("lj_loginwidget_password"));
        loginField.sendKeys("test01234");
        passwordField.sendKeys("Test123");

        WebElement login = driver.findElement(By.name("action:login"));
        login.click();
    }

    @Test
    void removeFavTest() {
        driver.findElement(By.cssSelector(".s-header-item__link--user")).click();
        driver.findElement(By.xpath(".//a[@title='Избранное']")).click();

        WebElement element = driver.findElement(By.cssSelector(".entryunit:nth-child(1)"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();

        driver.findElement(By.id("link.id")).click();
        WebElement deleteButton = driver.findElement(By.xpath(".//button[contains(text(),'Удалить')]"));
        Assertions.assertTrue(deleteButton.isEnabled());
        deleteButton.click();
    }

    @AfterAll
    static void close() {
        driver.quit();
    }
}
