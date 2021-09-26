package com.hogwarts;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static DriverFactory instance;
    public static DriverFactory getInstance() {
        if(instance==null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    public WebDriver createDriver(String caps) throws MalformedURLException {
        String platformName=caps;
        WebDriver driver=null;
        if(platformName.equals("android")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("deviceName", "ceshiren.com");
            capabilities.setCapability("appPackage", "com.tencent.wework");
            capabilities.setCapability("appActivity", ".launch.LaunchSplashActivity");
            capabilities.setCapability("noReset", "true");
            capabilities.setCapability("dontStopAppOnReset", "true");
            driver = new AppiumDriver<>(
                    new URL("http://0.0.0.0:4723/wd/hub"),
                    capabilities);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //隐式等待
            return driver;


        }else if(platformName.equals("ios")){

        }else if(platformName.equals("firefox")){

        }
        return driver;
    }
}
