package com.hogwarts.framework;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实战2-数据驱动：读到的Yaml参数是一个类(code)
 *
 * search.yaml文件
 *
 * data:
 * - a
 * - b
 * - c
 *
 *
 * steps:
 *   - chrome: {}
 *   - implicitly_wait: 5
 *   - get_url: https://ceshiren.com/
 *   - find: { id: search-button }
 *   - click: {}
 *   - find: { id: search-term }
 *   - send_keys: { data }
 */
public class TestCase {
    public  List<String> data;
    public  List<HashMap<String,Object>> steps;
    public  ChromeDriver driver;
    public  WebElement currentElement;


    /**
     * 再写驱动引擎运行它
     */
    public void run(){
        steps.forEach(step->{
            if(step.keySet().contains("chrome")){
                driver = new ChromeDriver();
            }
            if(step.keySet().contains("implicitly_wait")){
                driver.manage().timeouts().implicitlyWait(
                        (int) step.getOrDefault("implicitly_wait",5), TimeUnit.SECONDS);
            }
            if(step.keySet().contains("get_url")){
                driver.get( step.get("get_url").toString()) ;
            }

            if(step.keySet().contains("find")){
                ArrayList<By> bys= new ArrayList<>();
                ( (HashMap<String,String>)step.get("find") ).entrySet().forEach(stringStringEntry -> {
                    if(stringStringEntry.getKey().contains("id")){
                        bys.add(By.id(stringStringEntry.getValue()));
                    }
                    if(stringStringEntry.getKey().contains("xpath")){
                        bys.add(By.xpath(stringStringEntry.getValue()));
                    }
                    //类推...

                });
                currentElement = driver.findElement(bys.get(0));
            }

            if(step.keySet().contains("click")){
                currentElement.click();
            }
            if(step.keySet().contains("send_keys")){
                //todo： 参数化
                currentElement.sendKeys("search demo");
            }



        });

    }
}
