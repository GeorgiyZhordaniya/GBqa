package org.example.task5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AddFavTest {
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

        try {
            Thread.sleep(5000);  //необходимо для прогрузки страницы
        } catch (InterruptedException e) {}
    }

    @Test
    void addFavTest() {
        WebElement post = driver.findElement(By.cssSelector(".post-card--big > .post-card__link"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", post);
        post.click();

        WebElement favButton = driver.findElements(
                By.xpath(".//button[@title='Добавить в избранное']")).get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", favButton);
        favButton.click();
        Assertions.assertTrue(driver.findElement(By.cssSelector(".popup-1-2-32")) != null);
    }

    @AfterAll
    static void close() {
        driver.quit();
    }
}
