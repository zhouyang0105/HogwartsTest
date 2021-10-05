package com.hogwarts.service;


import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.Test;
import static spark.Spark.*;
import java.io.IOException;

/**
 * Mock ： browsermob-proxy
 */
public class BMPTest {

    @Test
    public void bmp() throws IOException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(8080);
        int port = proxy.getPort(); // get the JVM-assigned port

        // Selenium or HTTP client configuration goes here

        proxy.addResponseFilter((response, contents, messageInfo) -> {
            if(messageInfo.getOriginalUrl().contains(".json")){
                //todo: json -> hashmap -> rescue -> hashmap -> json
                //对数据进行修改
               String contentNew =  contents.getTextContents().replaceAll(":\"[^\"]*\"","null"); // 比如 把某个值改成null
               contents.setTextContents(contentNew);
            }
        });

        proxy.addRequestFilter((request,contents,messageInfo) -> {
            request.setUri("/");
            //contents.setTextContents(" ");
            return null;
        });

        System.in.read();
    }


    /**
     * Mock: sparkjava
     * 可以完成1个简单retful接口的请求
     */
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
        get("/proxy", (req, res) -> {
            BrowserMobProxy proxy = new BrowserMobProxyServer();
            proxy.start(8080);
            return null;
        });
    }









}
