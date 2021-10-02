package com.hogwarts.framework.potest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 通用的功能
 */
public class TestCase {

    public List<String> data;
    public  List<HashMap<String,Object>> steps;
    public int index=0;

    /**
     * 实战3 - 测试用例裂变，基于数据字典生成多分测试用例
     * 优化1：search.yaml 中 data有数据，进行循环。
     * @return
     */
    public List<POTestCase> testcase_generate(){

        List<POTestCase> testCasesList = new ArrayList<>();
        for(int i = 0; i < data.size(); i++) {
            POTestCase testcaseNew = new POTestCase();
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
    public Object getValue( HashMap<String,Object> step, String key){
        Object  value= step.get(key);
        if( value instanceof  String){
            //进行替换，简单demo示例。 TODO:复杂结构支持
            return ((String) value).replace("${data}", data.get(index));
        }else{
            return value;
        }
    }
    public Object getValue( HashMap<String,Object> step, String key, Object defaultValue){
        return step.getOrDefault(key, defaultValue);
    }


    public  void run(){

    }



}
