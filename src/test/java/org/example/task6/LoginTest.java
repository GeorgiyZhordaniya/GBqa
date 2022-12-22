package org.example.task6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.utils.CustomEventListener;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    static EventFiringWebDriver driver;
    @BeforeAll
    static void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new CustomEventListener());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.livejournal.com/");
    }

    @Test
    @DisplayName("Авторизация")
    @Description("Вход по логину и паролю")
    @Issue(" https://www.livejournal.com/")
    @Severity(SeverityLevel.BLOCKER)
    void loginTest() {
        boolean loginResult = new MainPage(driver)
                .clickLogin()
                .enterLogin("test01234")
                .enterPassword("Test123")
                .enterSite()
                .noError();
        Assertions.assertTrue(loginResult);
    }

    @AfterAll
    static void close() {
        driver.quit();
    }

    @AfterEach
    public void checkBrowser() {
        List<LogEntry> logs = getDriver().manage().logs().get(LogType.BROWSER).getAll();
        if(!logs.isEmpty()){
            if (logs.size() > 0 ) {
                logs.forEach(logEntry -> {
                    System.out.println(logEntry.getMessage());
                });
            }
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
