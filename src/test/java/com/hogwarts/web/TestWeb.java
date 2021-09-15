package com.hogwarts.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 企业微信自动登录实战
 */
public class TestWeb {


    /**
     * 测试人社区搜索功能
     */
   @Test
   public void testSearch(){

       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //隐式等待
       driver.get("https://ceshiren.com");
       //driver.findElement(By.cssSelector("#search-button")).click();或
       driver.findElement(By.cssSelector(".search-dropdown .d-icon-search")).click();
       driver.findElement(By.cssSelector("#search-term")).sendKeys("selenium");

   }

    /**
     * 企业微信扫码登录
     */
   @Test
    void testLogin() throws IOException, InterruptedException {
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //隐式等待
       //driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome_baidu");
       driver.get("https://work.weixin.qq.com/wework_admin/frame");
       //driver 20s
       Thread.sleep(20000);
       Set<Cookie> cookies = driver.manage().getCookies();//取cookie 打印出来 ,进行存储:gson-对Java进行序列化和反件中

       ObjectMapper mapper = new ObjectMapper(new YAMLFactory());// 把cookies存下来
       mapper.writeValue(new File("cookies.yaml"),cookies);

   }

    @Test
    void testLogined() throws IOException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //隐式等待
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());// 读取cookies
        TypeReference typeReference = new TypeReference<List<HashMap<String,Object>>>(){};
        List<HashMap<String,Object>> cookies = (List<HashMap<String, Object>>) mapper.readValue(new File("cookies.yaml"),typeReference);
        System.out.println(cookies);

        cookies.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie( cookieMap.get("name").toString() , cookieMap.get("value").toString()));
        });

        driver.navigate().refresh();//刷新


    }


}
