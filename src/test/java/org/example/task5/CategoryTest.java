// Проверка перехода в категорию

package org.example.task5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class CategoryTest {
    static WebDriver driver;
    @BeforeAll
    static void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.livejournal.com/");

//        WebElement loginButton = driver.findElement(By.cssSelector(".s-header-item__link--login"));
//        loginButton.click();
//
//        WebElement loginField = driver.findElement(By.id("user"));
//        WebElement passwordField = driver.findElement(By.id("lj_loginwidget_password"));
//        loginField.sendKeys("test01234");
//        passwordField.sendKeys("Test123");
//
//        WebElement login = driver.findElement(By.name("action:login"));
//        login.click();
    }

    @Test
    void categoryTest() {
        driver.findElement(By.xpath("//a[contains(text(),'Новые лица')]")).click();
        Assertions.assertEquals("https://www.livejournal.com/media/novye_lica/", driver.getCurrentUrl());
    }
    @AfterAll
    static void close() {
        driver.quit();
    }
}
