package com.hogwarts.app;

import com.hogwarts.DriverFactory;
import com.hogwarts.app.contact.ContactPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

/**
 * 4. app 自动化
 * 首页PO.
 * 功能入口页
 */
public class MainPage extends BasePage {

    /**
     * 生成构造器: 带参数
     * @param driver
     */
    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * 生成构造器: 不带参数
     * @throws MalformedURLException
     */
    public MainPage() throws MalformedURLException {
        String caps=System.getenv("driver");

        driver= (AppiumDriver) DriverFactory.getInstance().createDriver(caps);

        new WebDriverWait(driver, 120)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='通讯录']")));
    }

    /**
     * 功能：进入通讯录
     * 使用实例，不存在多线程的问题。
     * @return
     */
    public ContactPage contact() {
        driver.findElement(By.xpath("//*[@text='通讯录']")).click();
        return new ContactPage(driver);
    }
}
