package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage {

    private final WebDriver driver;


    @FindBy(xpath = "//label[.='Email']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(xpath = "//label[.='Пароль']/following-sibling::input")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class, 'button_button__33qZ0')][.='Войти']")
    private WebElement loginButton;


    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Авторизация пользователя с email, password")
    public void login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}