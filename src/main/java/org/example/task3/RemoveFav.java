//Удалить пост другого пользователя из избранного

package org.example.task3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class RemoveFav {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.livejournal.com/");

        Login.login(driver);

        driver.findElement(By.cssSelector(".s-header-item__link--user")).click();
        driver.findElement(By.xpath(".//a[@title='Избранное']")).click();

        WebElement element = driver.findElement(By.cssSelector(".entryunit:nth-child(1)"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();

        driver.findElement(By.id("link.id")).click();
        driver.findElement(By.xpath(".//button[contains(text(),'Удалить')]")).click();

//        driver.quit();
    }
}
