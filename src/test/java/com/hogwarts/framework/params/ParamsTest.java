package com.hogwarts.framework.params;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 5.UI自动化测试框架封装实战--打造自己的测试框架
 */
public class ParamsTest {

   /**
    * demoTest()
    * @param argument
    */
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }


   /**
    * 基本demo框架
    */
    @Test
    void  search(){
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click(); //搜索按钮
        driver.findElement(By.id("search-term")).sendKeys("search demo");//点击搜索输入框
    }



    /**
     * 参数化框架2--@ValueSource
     */
    @ParameterizedTest
    @ValueSource( strings = {"search demo1","search demo2"} )
    void  search2(String keyWord){
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click(); //搜索按钮
        driver.findElement(By.id("search-term")).sendKeys(keyWord);//点击搜索输入框
    }




    /*
    * 参数化框架3--@MethodSource
    * @MethodSource : 通过方法通过参数。注解后没有通过方法，自动寻找同方法名的数据。seach3()
    */
    @ParameterizedTest
    @MethodSource
    void search3(String keyWord){
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click(); //搜索按钮
        driver.findElement(By.id("search-term")).sendKeys(keyWord);//点击搜索输入框

    }
    static Stream<String> search3(){
        return Stream.of("search demo1","search demo2");
    }



    /**
     * 参数化框架4--数据驱动
     * 从外界获取参数，代码不在再受参数影响。
     *
     * 在前面3的基础上，只修改了参数获取部分。
     */
    @ParameterizedTest
    @MethodSource
    void search4(String keyword){
        //todo 测试步骤
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click(); //搜索按钮
        driver.findElement(By.id("search-term")).sendKeys(keyword);//点击搜索输入框

    }
    static List<String> search4() throws IOException {
        // return Stream.of("search demo1","search demo2");

        //github: jackson
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference = new TypeReference<List<String>>() {
        };

        /*//调试
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("demo");
        System.out.println(mapper.writeValueAsString(list));

        String path = ParamsTest.class.getResource("/framework/search.yaml").getPath();
        System.out.println(path);

        File file = new File(path);

        System.out.println(FileUtils.readFileToString(file ,"UTF-8"));

*/

         List<String> keywords =  (List<String>) mapper.readValue(
                ParamsTest.class.getResourceAsStream("/framework/search.yaml"),
                typeReference);
        return keywords;

    }

}
