package com.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * web 自动化
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
