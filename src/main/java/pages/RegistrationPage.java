package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;


    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton;

    @FindBy(css = ".Auth_link__1fOlj")
    private WebElement registerLink;

    @FindBy(css = "input[name='name']")
    private WebElement usernameInput;

    @FindBy(xpath = "//label[.='Email']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(xpath = "//label[.='Пароль']/following-sibling::input")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[.='Зарегистрироваться']")
    private WebElement submitButton;


    @FindBy(className = "input__error")
    private WebElement errorMessage;


    @FindBy(linkText = "Войти")
    private WebElement loginLink;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @Step("Открытие страницы личного кабинета")
    public void openPersonalAccountPage() {
        personalAccountButton.click();
    }


    @Step("Переход на страницу регистрации")
    public void navigateToRegisterPage() {
        registerLink.click();
    }


    @Step("Заполнение имени пользователя {username}")
    public void fillUsername(String username) {
        usernameInput.sendKeys(username);
    }


    @Step("Заполнение e-mail {email}")
    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }


    @Step("Заполнение пароля {password}")
    public void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }


    @Step("Отправка регистрационной формы")
    public void submitForm() {
        submitButton.click();
    }


    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }


    @Step("Проверка видимости ошибки")
    public boolean isErrorVisible() {
        return errorMessage.isDisplayed();
    }


    @Step("Кликаем по ссылке 'Войти' на странице регистрации")
    public void clickLoginFromRegistration() {
        loginLink.click();
    }
    @Step("Ожидание перехода на страницу авторизации")
    public void waitForRedirectToLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.urlContains("login")); // Ждем, пока URL изменится на /login
        } catch (TimeoutException ex) {
            throw new AssertionError("Переход на страницу авторизации не произошел", ex);
        }
    }
}