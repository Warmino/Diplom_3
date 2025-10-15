package API;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class UserSteps {
    private User user;
    private final static String REGISTER_ENDPOINT = "/api/auth/register";
    private final static String LOGIN_ENDPOINT = "/api/auth/login";
    private final static String DELETE_USER_ENDPOINT = "/api/auth/user";


    static {
        baseURI = "https://stellarburgers.education-services.ru";
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Step("Регистрация пользователя через API")
    public Response registerUser(User user) {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(user) // Передаем объект User напрямую
                .baseUri(baseURI); // Установите правильный URI
        Response response = request.post(REGISTER_ENDPOINT);


        String accessToken = response.jsonPath().getString("accessToken");
        String refreshToken = response.jsonPath().getString("refreshToken");


        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshToken);

        return response;
    }

    @Step("Авторизация пользователя через API")
    public Response loginUser(User user) {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(user);

        return request.post(LOGIN_ENDPOINT);
    }

    @Step("Удаление пользователя")
    public Response deleteUser() {
        return given()
                .header("Authorization", "Bearer " + user.getAccessToken())
                .when()
                .delete(DELETE_USER_ENDPOINT);
    }
}
