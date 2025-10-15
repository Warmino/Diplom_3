package pages;

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


    public void navigateToForgottenPasswordPage() {
        restorePasswordLink.click();
    }


    public void clickLoginFromForgottenPassword() {
        loginLink.click();
    }
}