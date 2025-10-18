import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import org.junit.Assert;
import org.junit.Test;





public class RegistrationTests extends BaseTest {

    @Test
    @DisplayName("Тест успешной регистрации пользователя")
    @Description("Проверяем успешную регистрацию нового пользователя через UI.")
    public void testSuccessfulUserRegistration() {
        String TEST_USERNAME = faker.name().username();
        String TEST_EMAIL = faker.internet().emailAddress();
        String TEST_PASSWORD = faker.internet().password();

        registrationPage.openPersonalAccountPage();
        registrationPage.navigateToRegisterPage();
        registrationPage.fillUsername(TEST_USERNAME);
        registrationPage.fillEmail(TEST_EMAIL);
        registrationPage.fillPassword(TEST_PASSWORD);
        registrationPage.submitForm();
        registrationPage.waitForRedirectToLoginPage();

        Assert.assertTrue("Переход на страницу авторизации не произошёл", driver.getCurrentUrl().contains("login"));
    }


    @Test
    @DisplayName("Ошибка короткой длины пароля")
    @Description("Проверяем ошибку при попытке ввести короткий пароль.")
    public void testPasswordTooShortError() {

        String randomUsername = faker.name().firstName();
        String randomEmail = faker.internet().emailAddress();
        String shortPassword = "short";


        registrationPage.openPersonalAccountPage();
        registrationPage.navigateToRegisterPage();


        registrationPage.fillUsername(randomUsername);
        registrationPage.fillEmail(randomEmail);
        registrationPage.fillPassword(shortPassword);


        registrationPage.submitForm();


        Assert.assertTrue("Ошибка не видна!", registrationPage.isErrorVisible());


        Assert.assertEquals("Текст ошибки не соответствует ожидаемому!", "Некорректный пароль", registrationPage.getErrorMessage());
    }


}