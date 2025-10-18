package base;

import api.User;
import api.UserSteps;
import com.github.javafaker.Faker;
import config.BrowserSettings;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.ForgottenPasswordPage;
import pages.RegistrationPage;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected RegistrationPage registrationPage;
    protected Faker faker;
    protected UserSteps userSteps;
    protected User currentUser;
    protected AuthorizationPage authorizationPage;
    protected ForgottenPasswordPage forgottenPasswordPage;

    @Before
    public void setUp() {
        // Получаем тип браузера из аргументов командной строки или переменной окружения
        String browserType = System.getProperty("browser", "chrome");

        // Используем фабрику для создания драйвера
        driver = BrowserSettings.getDriver(browserType);

        driver.manage().window().maximize();
        driver.get("https://stellarburgers.education-services.ru");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        authorizationPage = new AuthorizationPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgottenPasswordPage = new ForgottenPasswordPage(driver);
        faker = new Faker();
        userSteps = new UserSteps();

    }

    @After
    public void tearDown() {
        if (currentUser != null && currentUser.getAccessToken() != null) {
            userSteps.deleteUser();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}