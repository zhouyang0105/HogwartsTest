package com.hogwarts.service.wechat.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Demo_01_base {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class); //打印日志

    static String accessToken;//定义类级别的变量
    static String departmentId;

    @BeforeAll
    public static void  getAccessToken(){
        accessToken = given().log().all()
                .when()
                .param("corpid","wwddcc304192c1fa76")
                .param("corpsecret", "gc0cRDMBfkS3t84AyW7xLcybD3E6hasmcoA2eA3OFuo")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .extract().response().path("access_token");
    }

    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment(){
        String body = "{\n" +
                "   \"name\": \"广州研发中心\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                "   \"id\": 2\n" +
                "}";
        Response response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        departmentId = response.path("id").toString();
        logger.info(accessToken);
    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment() {

        String body ="{\n" +
                "   \"id\": "+departmentId+",\n" +
                "   \"name\": \"根部门ID1\",\n" +
                "   \"name_en\": \"ROOT1\",\n" +
                "   \"order\": 1\n" +
                "}\n";
        Response response=given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken+"")
                .then()
                .log().body()
                .extract().response();
        assertEquals("0",response.path("errcode").toString());

    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void listDepartment() {

        Response response =given().log().all()
                .when()
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0",response.path("errcode").toString());
        assertEquals(departmentId,response.path("department.id[0]").toString());

    }
    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment() {
        Response response = given().log().all()
                .contentType("application/json")
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract().response()
                ;
        assertEquals("0",response.path("errcode").toString());

    }
}
















