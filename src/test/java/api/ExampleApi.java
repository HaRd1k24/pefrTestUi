package api;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExampleApi {
    Map<String, String> cookies;

    @BeforeEach
    void test() {
        cookies = given().accept(ContentType.JSON).when().get("https://mail.ru").getCookies();
        System.out.println(cookies);
    }

    @Test
    void auth() {
        Map<String, String> map = new HashMap<>();
        map.put("login", "lev-trapeznikov@mail.ru");
        map.put("password", "Eaa4HPpOyu2%");
        System.out.println(cookies);

        given().cookies(cookies).when().post("https://mail.ru").then().log().all();

    }

}
