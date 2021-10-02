package com.hogwarts.framework.potest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void search(String keyword){
       // driver.findElement(By.id("search-button")).click();
        click(By.id("search-button"));
        sendKeys( By.id("search-term"), keyword );
    }
}
