package com.hogwarts.app.contact;

import com.hogwarts.app.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContactPage extends BasePage {
    private By menu=By.id("i6i");  //注意修改：id里的内容每个版本可能都不一样
    private By searchButton=By.id("i6n");
    private By addDepartLocator=By.xpath("//*[@text='添加子部门']");
    private By editText=By.className("android.widget.EditText");
    private By departName=By.xpath("//android.widget.ListView//android.widget.TextView");
    private By closeButton=By.xpath("//*[contains(@resource-id, 'gpf') or contains(@resource-id, 'i6d')]");


    public ContactPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * 添加部门
     * @param name
     * @return
     */
    public ContactPage addDepart(String name){
        driver.findElement(menu).click();
        driver.findElement(addDepartLocator).click();
        driver.findElement(editText).sendKeys(name);
        driver.findElement(By.xpath("//*[@text='确定']")).click();
        driver.findElement(closeButton).click();

        return this;
    }

    /**
     * 查找部门
     * @param keyword
     * @return
     */
    public ContactPage search(String keyword){
        driver.findElement(searchButton).click();
        driver.findElement(editText).sendKeys(keyword);
        return this;
    }

    /**
     * 获取部门内容
     * @return
     */
    public String getCurrentDepartName(){
        StringBuilder contents=new StringBuilder();
        driver.findElements(departName).forEach(element->{
            contents.append(((WebElement)element).getText());
        });
        return contents.toString();
    }


}
