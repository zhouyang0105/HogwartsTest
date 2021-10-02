package com.hogwarts.framework.potest;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * java反射演示demo
 * 验证：小的单元测试 或 jshell
 *
 * 和其他类关联，单纯演示类
 */
public class ReflectTest {
    @Test
    void reflect() throws ClassNotFoundException {
        Class<?> str = Class.forName("java.lang.String");
//        System.out.println(str.getMethods());
        Arrays.stream(str.getMethods()).forEach(m->{  //str.getMethods().str  --- Arrays.stream(str.getMethods())
            System.out.println(m);
        });    //打印类里的每个方法
    }
}
