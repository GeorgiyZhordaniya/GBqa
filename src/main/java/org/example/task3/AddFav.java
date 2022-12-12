//Добавить пост другого пользователя в избранное

package org.example.task3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AddFav {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.livejournal.com/");

        Login.login(driver);


        try {
            Thread.sleep(3000);  //необходимо для прогрузки страницы
        } catch (InterruptedException e) {}
        driver.findElement(By.cssSelector(".post-card--big > .post-card__link")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable
                (By.xpath(".//button[@title='Добавить в избранное']")));

        driver.findElements(By.xpath(".//button[@title='Добавить в избранное']")).get(0).click();
        driver.findElement(By.cssSelector(".popup-1-2-32"));

//        driver.quit();
    }
}
