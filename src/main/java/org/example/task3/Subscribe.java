//Подписаться на автора

package org.example.task3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Subscribe {
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
        } catch (InterruptedException e) {        }
        driver.findElement(By.cssSelector(".post-card--big > .post-card__link")).click();

        driver.findElement(By.xpath(".//a[contains(text(), 'Подписаться')]")).click();
        driver.findElement(By.cssSelector(".sc-1jopm0p-6")).click();

        if (driver.findElements(By.xpath(".//a[contains(text(), 'Вы подписаны')]")).size() == 0) {
            System.exit(1);
        }
    }
}
