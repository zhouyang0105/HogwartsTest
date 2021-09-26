package com.hogwarts.app;

import io.appium.java_client.AppiumDriver;

/**
 * 基础功能封装
 */
public class BasePage {
    protected AppiumDriver driver;

    /**
     * 构造器：带参数
     * @param driver
     */
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    } //driver -> generator:constructor

    /**
     * 构造器:不带参数
     */
    public BasePage() {
    }
}
