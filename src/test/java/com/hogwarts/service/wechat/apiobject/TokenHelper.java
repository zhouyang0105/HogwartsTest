/**
 * projectName: WeChatWorkApiTest
 * fileName: TokenHelper.java
 * packageName: com.wechat.apiobject
 * date: 2020-12-19 4:24 下午
 */
package com.hogwarts.service.wechat.apiobject;

import static io.restassured.RestAssured.given;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: TokenHelper
 * @packageName: com.wechat.apiobject
 * @description: tokenhelper
 * @data: 2020-12-19 4:24 下午
 **/
public class TokenHelper {
    public static String getAccessToken(){
        String accessToken=given()
//                    .log().all()
                .when()
                .param("corpid","wwddcc304192c1fa76")
                .param("corpsecret", "gc0cRDMBfkS3t84AyW7xLcybD3E6hasmcoA2eA3OFuo")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
//                    .log().body()
                .extract()
                .response()
                .path("access_token");
        return accessToken;
    }

}