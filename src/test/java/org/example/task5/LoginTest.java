package org.example.task5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    static WebDriver driver;
    @BeforeAll
    static void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    void before() {
        driver.get("https://www.livejournal.com/");
    }

    @Test
    void loginTest() {
        WebElement loginButton = driver.findElement(By.cssSelector(".s-header-item__link--login"));
        loginButton.click();

        WebElement loginField = driver.findElement(By.id("user"));
        WebElement passwordField = driver.findElement(By.id("lj_loginwidget_password"));
        loginField.sendKeys("test01234");
        passwordField.sendKeys("Test123");

        WebElement login = driver.findElement(By.name("action:login"));
        login.click();

        try {
            Thread.sleep(1000);  //необходимо для прогрузки ошибки
        } catch (InterruptedException e) {}

        Assertions.assertTrue(
                driver.findElements(By.xpath("//span[contains(.,'Неверный пароль')]")).size() == 0
                || driver.findElements(By.xpath("//span[contains(.,'Имя не найдено')]")).size() == 0);
    }

    @AfterAll
    static void close() {
        driver.quit();
    }
}
