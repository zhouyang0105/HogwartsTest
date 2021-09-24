package hogwarts.app;

import com.ceshiren.hogwarts.DriverFactory;
import com.ceshiren.hogwarts.app.contact.ContactPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class MainPage extends BasePage {
    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    public MainPage() throws MalformedURLException {
        String caps=System.getenv("driver");
        driver= (AppiumDriver) DriverFactory.getInstance().createDriver(caps);
        new WebDriverWait(driver, 120)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='通讯录']")));
    }

    public ContactPage contact() {
        driver.findElement(By.xpath("//*[@text='通讯录']")).click();
        return new ContactPage(driver);
    }
}
