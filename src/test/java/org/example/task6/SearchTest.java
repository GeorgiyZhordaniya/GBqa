// Проверка поиска

package org.example.task6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.*;
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

//        new MainPage(driver)
//                .clickLogin()
//                .enterLogin("test01234")
//                .enterPassword("Test123")
//                .enterSite();
//        try {
//            Thread.sleep(3000);  //необходимо для прогрузки страницы
//        } catch (InterruptedException e) {}
    }

    @Test
    @DisplayName("Проверка поиска")
    @Description("Проверка поискового запроса")
    @Issue(" https://www.livejournal.com/")
    void searchFavTest() {
        String url = new MainPage(driver)
                .clickSearch()
                .searchTest()
                .getUrl();

        Assertions.assertEquals(
         "https://www.livejournal.com/rsearch?q=Test&sort=_score&searchArea=post",
                url);
    }

    @AfterAll
    static void close() {
        driver.quit();
    }
}
