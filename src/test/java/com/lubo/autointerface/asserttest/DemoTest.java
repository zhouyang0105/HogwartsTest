package com.lubo.autointerface.asserttest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DemoTest {
    @Test
    void fun(){
        given()
                .get("https://www.baidu.com")
                .then()
                .statusCode(200)
                .log().all();
    }

}
