package com.hogwarts.framework.potest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
public class POTestCase extends TestCase {
    public  ChromeDriver driver;
    public  WebElement currentElement;
    public  MainPage  mainPage;

    /**
     * 再写驱动引擎运行它
     */
    public void run(){
        steps.forEach(step -> {
            String key = step.keySet().iterator().next();
            String[] objectMethod = key.split(".");
            String object = objectMethod[0];
            String method = objectMethod[1];

            if(step.keySet().contains("MainPage")){
//                mainPage =  new MainPage();
                String[] value = (String[]) getValue(step, "init");
                BasePage.getInstance().poInit(value[0],value[1]);

            }
            if(step.keySet().contains("SearchPage")){
                SearchPage searchPage = new SearchPage(driver);
            }
            if(step.keySet().contains(".")){


//                if(method.equals("search")){
//                    mainPage.search();
//                }


                //解决了找方法的难题
                mainPage.stepRun(method);

            }


        });

    }
}
