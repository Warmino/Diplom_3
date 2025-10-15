import API.User;

import Base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;

public class RegistrationTests extends BaseTest {

    @Test
    @DisplayName("Тест успешной регистрации пользователя")
    @Description("Проверяем успешную регистрацию нового пользователя через UI и проверку авторизации через API.")
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


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("login"));


        currentUser = new User(TEST_EMAIL, TEST_PASSWORD, TEST_USERNAME);
        userSteps.setUser(currentUser);


        Response loginResponse = given()
                .header("Content-Type", "application/json")
                .body(currentUser)
                .when()
                .post("/api/auth/login");


        System.out.println("Server response status code: " + loginResponse.getStatusCode());
        System.out.println("Server response body: " + loginResponse.getBody().asString());


        loginResponse.then().assertThat()
                .statusCode(SC_OK)
                .contentType(ContentType.JSON)
                .body("success", is(true))
                .body("user.name", equalTo(TEST_USERNAME))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
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