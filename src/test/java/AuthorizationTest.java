import api.User;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import static org.junit.Assert.assertTrue;

public class AuthorizationTest extends BaseTest {

    private User testUser;

    @Before
    public void setupUser() {
        testUser = new User(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().username()
        );
        userSteps.registerUser(testUser);
    }

    @Test
    @DisplayName("Тест входа через кнопку 'Войти в аккаунт'")
    @Description("Проверка успешного входа через главную страницу сайта")
    public void testLoginViaMainPageEnterAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPersonalCabinet();
        authorizationPage.login(testUser.getEmail(), testUser.getPassword());


        assertTrue("Пользователь не вошёл в систему!", mainPage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Тест входа через кнопку 'Личный кабинет'")
    @Description("Проверка успешного входа через ссылку 'Личный кабинет'")
    public void testLoginUsingPersonalCabinetButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPersonalCabinet();
        authorizationPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue("Пользователь не вошёл в систему!", mainPage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Тест входа через форму регистрации")
    @Description("Проверка успешного входа через форму регистрации")
    public void testLoginViaRegistrationFormSubmitButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPersonalCabinet();
        registrationPage.navigateToRegisterPage();
        registrationPage.clickLoginFromRegistration();
        authorizationPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue("Пользователь не вошёл в систему!", mainPage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Тест входа через форму восстановления пароля")
    @Description("Проверка успешного входа через форму восстановления пароля")
    public void testLoginUsingForgottenPasswordFormSubmitButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPersonalCabinet();
        forgottenPasswordPage.navigateToForgottenPasswordPage();
        forgottenPasswordPage.clickLoginFromForgottenPassword();
        authorizationPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue("Пользователь не вошёл в систему!", mainPage.isUserLoggedIn());
    }
}