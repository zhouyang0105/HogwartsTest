package com.lubo.autointerface.asserttest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetTest {

    public static String access_token;

    /**
     * 1.2 取token 值打印出来
     *
     * from 服务端接口自动化
     */
    @BeforeAll
    public static void getMethod(){
        access_token = given()
                .params("corpid","wwddcc304192c1fa76","corpsecret","sBlC2OSmI14tR5VMrbHmnKZQHgAtkX0xKPO4U8vWERI")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
        //System.out.println(access_token);
    }

    /**1.2
     * 发送应用消息
     *
     * from 服务端接口自动化
     */
    @Test
    void postMethd(){
        given()
                .contentType("application/json; charset=utf-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"测试愉快。\\n具体查看<a href=\\\"http://work.weixin.qq.com\\\">测试资料分享</a>，一起加入吧。\"\n" +
                        "   }\n" +
                        "}  ")
                .queryParam("access_token",access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+access_token+"")
                .then()
                .log().all();
    }

}
