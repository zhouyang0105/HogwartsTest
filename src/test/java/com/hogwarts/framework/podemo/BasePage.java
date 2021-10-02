package com.hogwarts.framework.podemo;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 基础功能封装
 */
public class BasePage {

    WebDriver driver;

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

    void click(By by) {
        driver.findElement(by).click();
    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }


}

