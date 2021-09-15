package com.testcaseinfo.testcase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class LoginTest {
    private HashMap<String, Object> dataMap =  new HashMap<String, Object>();

    @Test
    void loginTest(){
        dataMap.put("login","登录成功");
        //System.out.println(dataMap.get("login"));
    }

    @Nested
    class PayTest{

        @Test
        void payTest(){
            if(null!=dataMap.get("buy")){
                System.out.println("正在支付，请等待");
                System.out.println(dataMap.get("buy"));
            }else{
                System.out.println("还未购买课程");
            }
        }

    }



    @Nested
    class BuyTest{

        @Test
        void buyTest(){
            if( dataMap.get("login").equals("登录成功") ){
                System.out.println("登录成功，可以买东西啦");
                dataMap.put("buy","定向课程4");
            }else{
                System.out.println("登录失败,重新登录");
            }

        }
    }

}
