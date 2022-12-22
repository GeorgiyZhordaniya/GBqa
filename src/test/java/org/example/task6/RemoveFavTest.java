package org.example.task6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.*;
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
    @DisplayName("Удаление поста из избранного")
    @Description("Удаление поста из избранного")
    @Issue(" https://www.livejournal.com/")
    void removeFavTest() {
        WebElement deleteButton = new MainPage(driver)
                .clickUser()
                .clickFav()
                .mouseOverControlFav()
                .clickControlFav()
                .getDeleteButton();

        Assertions.assertTrue(deleteButton.isEnabled());
        deleteButton.click();
    }

    @AfterAll
    static void close() {
 //       driver.quit();
    }
}
