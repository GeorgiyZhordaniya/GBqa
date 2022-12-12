//Вход на сайт под существующей учетной записью

package org.example.task3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginMain {
    public static void main(String[] args) {
//        WebDriver driver = WebDriverManager.chromedriver().create();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.livejournal.com/");

        Login.login(driver);

        try {
            Thread.sleep(1000);  //необходимо для прогрузки ошибки
        } catch (InterruptedException e) {}

        if(driver.findElements(By.xpath("//span[contains(.,'Неверный пароль')]")).size() != 0
                || driver.findElements(By.xpath("//span[contains(.,'Имя не найдено')]")).size() != 0) {
            System.exit(1);
        }
//        try {
//        driver.findElement(By.xpath("//span[contains(.,'Имя не найдено')]"));
//        } catch (Exception e) {}
//        try {
//            driver.findElement(By.xpath("//span[contains(.,'Неверный пароль')]"));
//        } catch (Exception e) {}

//        driver.quit();
    }
}
