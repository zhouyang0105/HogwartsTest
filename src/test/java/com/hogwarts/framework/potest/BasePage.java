package com.hogwarts.framework.potest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 基础功能封装
 */
public class BasePage {

    WebDriver driver;
    HashMap<String,Object> pages = new HashMap<>();
    static BasePage instance = null;  //单例


    /**
     * 构造器：带参数
     * @param driver
     */
    public BasePage(WebDriver driver) {
        this.driver=driver;
    }

    /**
     * 构造器：不带参数
     */
    public BasePage() {
    }


    public static BasePage getInstance() {  // geti. 用单例存储所有PO
        if( instance == null){
            instance = new BasePage();
        }
        return instance;
    }
    
    void click(By by) {
        driver.findElement(by).click();
    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }


    void poInit(String name,String className){ // BasePage getInstance()
        try {
            Class<?> pageClass = Class.forName(name);
            pages.put(name,pageClass);

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Java反射
     */
    void stepRun(String method)  {
//        if(method.equals("search")){ //注册方法使用，不建议
//            this.search();
//        }

        Method methodJava = Arrays.stream(this.getClass().getMethods())   // 方法集。 获取当前类的方法，转换成数据流，过滤，方法，获取值
                .filter(m->m.getName().equals(method))
                .findFirst().get();

        try {
            //等价于 this.search(). 反射自己调自己
            methodJava.invoke(this);
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        } catch(InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}

