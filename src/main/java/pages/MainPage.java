package pages;

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


    public void clickEnterAccountButton() {
        enterAccountButton.click();
    }


    public void openPersonalCabinet() {
        personalCabinetButton.click();
    }
}
