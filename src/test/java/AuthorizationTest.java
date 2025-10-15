import API.User;
import Base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.*;

public class AuthorizationTest extends BaseTest {

    @Test
    @DisplayName("Тест входа через кнопку 'Войти в аккаунт'")
    @Description("Проверка успешного входа через главную страницу сайта")
    public void testLoginViaMainPageEnterAccountButton() {
        MainPage mainPage = new MainPage(driver);


        User user = new User(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().username()
        );
        userSteps.registerUser(user);


        mainPage.clickEnterAccountButton();
        authorizationPage.login(user.getEmail(), user.getPassword());


        userSteps.loginUser(user)
                .then()
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Тест входа через кнопку 'Личный кабинет'")
    @Description("Проверка успешного входа через ссылку 'Личный кабинет'")
    public void testLoginUsingPersonalCabinetButton() {
        MainPage mainPage = new MainPage(driver);
        User user = new User(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().username()
        );
        userSteps.registerUser(user);


        mainPage.openPersonalCabinet();
        authorizationPage.login(faker.internet().emailAddress(), faker.internet().password());


        userSteps.loginUser(user)
                .then()
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Тест входа через форму регистрации")
    @Description("Проверка успешного входа через форму регистрации")
    public void testLoginViaRegistrationFormSubmitButton() {
        MainPage mainPage = new MainPage(driver);
        User user = new User(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().username()
        );
        userSteps.registerUser(user);


        mainPage.openPersonalCabinet();
        registrationPage.navigateToRegisterPage();
        registrationPage.clickLoginFromRegistration();
        authorizationPage.login(faker.internet().emailAddress(), faker.internet().password());


        userSteps.loginUser(user)
                .then()
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Тест входа через форму восстановления пароля")
    @Description("Проверка успешного входа через форму восстановления пароля")
    public void testLoginUsingForgottenPasswordFormSubmitButton() {
        MainPage mainPage = new MainPage(driver);
        User user = new User(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().username()
        );
        userSteps.registerUser(user);


        mainPage.openPersonalCabinet();
        forgottenPasswordPage.navigateToForgottenPasswordPage();
        forgottenPasswordPage.clickLoginFromForgottenPassword();
        authorizationPage.login(faker.internet().emailAddress(), faker.internet().password());


        userSteps.loginUser(user)
                .then()
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

}