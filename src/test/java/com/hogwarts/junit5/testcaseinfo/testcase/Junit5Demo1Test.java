package com.hogwarts.junit5.testcaseinfo.testcase;


import org.junit.jupiter.api.*;

public class Junit5Demo1Test {

    @BeforeAll
    public static void before(){ //没有static不会执行
        System.out.println("before");
    }

    @BeforeEach
    public  void beforeEach(){
        System.out.println("beforeEach");
    }

   @Test
   @DisplayName("fun 测试方法")
   //@RepeatedTest(5)   //重复执行
   @Tag("testdemo")
   public void fun(){
        System.out.println("fun");
    }

    @Test
   // @Disabled
   // @DisplayName("fun1 测试方法")
    @Tag("uatdemo")
    public void  fun1(){
        System.out.println("fun1");
    }

    @Test
    @Tag("testdemo")
    void fun2(){
        System.out.println("fun2");
    }

    @AfterEach
    public  void afterEach(){
        System.out.println("afterEach");
    }

    @AfterAll
    public  static void after(){
        System.out.println("after");
    }
}
