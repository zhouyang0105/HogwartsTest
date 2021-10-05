package com.lubo.autointerface.asserttest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * 对json文件进行断言
 */
public class JsonAssertTest {

    @Test
    public void  testJson(){
        given()
                .when().get("http://127.0.0.1:8000/testerhome.json")
                .then()
                .body("lotto.lottoId",equalTo(5))
                .body("lotto.winners.winnerId", hasItems(23,54));  //lotto.winners.winnerId其中winners是多个元素， 以数组形式展现出来
                //.body("**.lottoId", equalTo(5))
    }

    @Test
    public void  testJson1(){
        given()
                .when().get("http://127.0.0.1:8000/testerhome.json")
                .then()
                .body("store.book.category",hasItems("reference"))
                .body("store.book[0].authr", equalTo("Nigel Rees")) // 第一本书的作者 进行断言
                .body("store.book..findAll{ book -> book.price == 8.95f }.price", equalTo("8.95f"))
                .body("store.book..findAll{ book -> book.price == 8.95f }.title", equalTo("8.95f"));
    }

    @Test
    public void testXml(){
        given()
                .when().get("http://127.0.0.1:8000/testerhome.json")
                .then()
                    .body("shopping.category.item[0].name", equalTo("Chocolate"))
                    .body("shopping.category.item.size()", equalTo(5))
                    .body("shopping.category.findAll { it.@type == 'groceries' }.size()", equalTo(1)) //找有几个category
                    .body("**.findAll( it.price == 20).name", equalTo("Coffee"));
    }
}
