package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private final WebDriver driver;


    @FindBy(xpath = "//button[.='Войти в аккаунт']")
    private WebElement enterAccountButton;

    @FindBy(linkText = "Личный Кабинет")
    private WebElement personalCabinetButton;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickEnterAccountButton() {
        enterAccountButton.click();
    }

    @Step("Открытие личного кабинета")
    public void openPersonalCabinet() {
        personalCabinetButton.click();
    }

    public boolean isUserLoggedIn() {
        return driver.getCurrentUrl().startsWith("https://stellarburgers.education-services.ru/");
    }
}
