package hogwarts.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 4. app实战
 */
public class WeWorkClassicTest {

    @Test
    void  search() throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","android");
        capabilities.setCapability("udid","emulator-5554");
        capabilities.setCapability("deviceName","xxxx");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage","com.tencent.wework");//企业微信
        capabilities.setCapability("appActivity",".launch.LaunchSplashActivity");
        capabilities.setCapability("noReset","true"); //不重置数据，不然又要重新登录

        AppiumDriver<MobileElement>  driver = new AppiumDriver<>(
                new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS); //隐式全局等待
        driver.findElement(MobileBy.id("izx")).click(); //每个版本id 都是不一样的
        //driver.findElement(MobileBy.id("izm")).click();
        //driver.findElement(MobileBy.id("hj9")).sendKeys("hogwarts");

    }


    //contact profile 用例并行和串行考虑
}
