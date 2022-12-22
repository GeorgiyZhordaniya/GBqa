package org.example.task6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

        new MainPage(driver)
                .clickLogin()
                .enterLogin("test01234")
                .enterPassword("Test123")
                .enterSite();
        try {
            Thread.sleep(3000);  //необходимо для прогрузки страницы
        } catch (InterruptedException e) {}
    }

    @Test
    void addFavTest() {
        new MainPage(driver).clickPost();

        WebElement favPopUp = new PostPage(driver)
                .addFav()
                .addFavPopUp();
        Assertions.assertTrue(favPopUp != null);
    }

    @AfterAll
    static void close() {
        driver.quit();
    }
}
