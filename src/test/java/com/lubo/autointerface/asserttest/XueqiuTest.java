package com.lubo.autointerface.asserttest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.naming.ldap.HasControls;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

import static org.hamcrest.CoreMatchers.equalTo;

public class XueqiuTest {
    public static String  code;

    @BeforeClass
    public static void login(){
        useRelaxedHTTPSValidation(); //请求是https的
        RestAssured.proxy("127.0.0.1", 8888); // 全局变量 诶个接口都设置代理
        //loginXueqiu();
    }

    @Test
    public static void loginXueqiu(){
        code =given()
                // .proxy("127.0.0.1", 8888)
                .header("User-Agent","Xueqiu Android 12.6.1")  //对应Charles的Headers
                .queryParam("_t","1NETEASEe621266655e017a15d92cbaac69eef3d.9044662600.1633265238109.1633265409660")
                .queryParam("_s", "482292")  // Request 或 Query String
                .cookie("u", "9044662600") //cookie里其他参数 依次类推
                .cookie("xq_a_token", "59c93d90b7e66bb5f331cb761982198fc153aa67")
                .formParam("grant_type","password")    // Text  form格式 。有些非必要的可以不用添加
                .formParam("telephone","15675154076")
                .formParam("password"," ")
                .formParam("areacode","86")
                .formParam("client_id","JtXbaMn7eP")
                .formParam("client_secret","txsDfr9FphRSPov5oQou74")
                .when().post("https://api.xueqiu.com/provider/oauth/token")
                .then()
                .log().all()
                .statusCode(400)
                //  .body("error_code", equalTo("20123"))
                .extract().path("error_code");
        //.extract().response();

        //System.out.println(response);
        System.out.println(code);
    }

    @Test
    public void testPostJson(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("a",1);
        map.put("b","testerhome");
        map.put("array", new String[] {"111","222"});
        given()
                .contentType(ContentType.JSON)
                .body(map)
        .when().post("http:www.baidu.com")
        .then()
                .log().all()
                .statusCode(200);
    }


}
