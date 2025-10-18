package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BurgerBuilderSectionsPage {

    private final WebDriver driver;

    @FindBy(xpath = "//span[text()='Булки']")
    private WebElement bunsSectionTab;

    @FindBy(xpath = "//span[text()='Соусы']")
    private WebElement saucesSectionTab;

    @FindBy(xpath = "//span[text()='Начинки']")
    private WebElement fillingsSectionTab;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab_type_current')]")
    private WebElement activeSectionIndicator;

    public BurgerBuilderSectionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @Step("Переход к секции 'Булки'")
    public void navigateToBunsSection() {
        bunsSectionTab.click();
    }
    @Step("Переход к секции 'Соусы'")
    public void navigateToSaucesSection() {
        saucesSectionTab.click();
    }
    @Step("Переход к секции 'Начинки'")
    public void navigateToFillingsSection() {
        fillingsSectionTab.click();
    }
    @Step("Проверка активной секции ")
    public boolean isSectionActive(String sectionName) {
        return activeSectionIndicator.getText().equals(sectionName);
    }
}