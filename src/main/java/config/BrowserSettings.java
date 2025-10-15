package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserSettings {
    public static final String APP_URL = "https://stellarburgers.education-services.ru";


    public static WebDriver createChromeDriver() {
        return new ChromeDriver();
    }


    public static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
        return new ChromeDriver(options);
    }

}