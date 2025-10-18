package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPasswordPage {

    private final WebDriver driver;


    @FindBy(linkText = "Восстановить пароль")
    private WebElement restorePasswordLink;


    @FindBy(linkText = "Войти")
    private WebElement loginLink;


    public ForgottenPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Переход на страницу восстановления пароля")
    public void navigateToForgottenPasswordPage() {
        restorePasswordLink.click();
    }

    @Step("Нажатие на кнопку 'Войти' на странице восстановления пароля")
    public void clickLoginFromForgottenPassword() {
        loginLink.click();
    }
}