// Проверка поиска

package org.example.task5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchTest {
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

        try {
            Thread.sleep(5000);  //необходимо для прогрузки страницы
        } catch (InterruptedException e) {}
    }

    @Test
    void searchFavTest() {
        driver.findElements(By.cssSelector(".flaticon--search")).get(1).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        WebElement searchField = driver.findElement(By.cssSelector(".s-inline-search-input"));
        searchField.sendKeys("Test", Keys.ENTER);

        try {
            Thread.sleep(5000);  //необходимо для прогрузки страницы
        } catch (InterruptedException e) {}

        List<String> windowHandles = new ArrayList(driver.getWindowHandles());
        String secondTab = windowHandles.get(1);

        driver.switchTo().window(secondTab);


        Assertions.assertEquals(
         "https://www.livejournal.com/rsearch?q=Test&sort=_score&searchArea=post",
                driver.getCurrentUrl());
    }

    @AfterAll
    static void close() {
        driver.quit();
    }
}
