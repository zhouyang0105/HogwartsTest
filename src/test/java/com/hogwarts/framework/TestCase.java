package com.hogwarts.framework;


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
public class TestCase {
    public  List<String> data;
    public  List<HashMap<String,Object>> steps;
    public  ChromeDriver driver;
    public  WebElement currentElement;
    public int index=0;

    /**
     * 实战3 - 测试用例裂变，基于数据字典生成多分测试用例
     * 优化1：search.yaml 中 data有数据，进行循环。
     * @return
     */
    public List<TestCase> testcase_generate(){
//        data.forEach(item->{
//              TestCase testcaseNew =   new TestCase();
//              testcaseNew.index=i;
//
//        });

        List<TestCase> testCasesList = new ArrayList<>();
        for(int i = 0; i < data.size(); i++) {
            TestCase testcaseNew = new TestCase();
            testcaseNew.index=i;

            //对数据赋值，不然会报空指针异常
            testcaseNew.steps= steps;
            testcaseNew.data = data;

            testCasesList.add(testcaseNew);
        }
        return testCasesList;
    }

    /**
     * 替换yaml 中的一些变量，复杂结构需要用递归
     *
     * 对step.get 进行了一次封装
     * 对之前step.get进行替换
     *
     * 优化： 新增一个带默认值的参数
     * @param step
     * @param key
     * @return
     */
    private Object getValue( HashMap<String,Object> step, String key){
        Object  value= step.get(key);
        if( value instanceof  String){
            //进行替换，简单demo示例。 TODO:复杂结构支持
           return ((String) value).replace("${data}", data.get(index));
        }else{
            return value;
        }
    }
    private Object getValue( HashMap<String,Object> step, String key, Object defaultValue){
        return step.getOrDefault(key, defaultValue);
    }




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
                        (int) getValue(step,"implicitly_wait",5), TimeUnit.SECONDS);
            }
            if(step.keySet().contains("get_url")){
                driver.get( getValue(step,"get_url").toString()) ;
            }

            if(step.keySet().contains("find")){
                ArrayList<By> bys= new ArrayList<>();
                ( (HashMap<String,String>) getValue(step,"find") ).entrySet().forEach(stringStringEntry -> {
                    if(stringStringEntry.getKey().contains("id")){
                        bys.add(By.id(stringStringEntry.getValue()));
                    }
                    if(stringStringEntry.getKey().contains("xpath")){
                        bys.add(By.xpath(stringStringEntry.getValue()));
                    }
                    //类推...id xpath css

                });
                currentElement = driver.findElement(bys.get(0));
            }

            if(step.keySet().contains("click")){
                currentElement.click();
            }
            if(step.keySet().contains("send_keys")){
                //todo： 参数化
                currentElement.sendKeys( getValue(step,"send_keys").toString() );
            }

            if(step.keySet().contains("quit")){
                driver.quit();
            }


        });

    }
}
