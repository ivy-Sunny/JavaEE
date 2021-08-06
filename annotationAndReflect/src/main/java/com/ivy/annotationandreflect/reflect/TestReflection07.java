package com.ivy.annotationandreflect.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TestReflection07
 * 分析性能问题
 *
 * @Author: ivy
 * @CreateTime: 2021-08-06
 */
public class TestReflection07 {
    /**
     * 普通方式调用
     */
    public static void test01() {
        User user = new User();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            user.getName();
        }
        System.out.println("普通方式执行10亿次耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 反射方式调用
     */
    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            getName.invoke(user, null);
        }
        System.out.println("反射方式执行10亿次耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 反射方式调用，关闭检测
     */
    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);
        getName.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            getName.invoke(user, null);
        }
        System.out.println("关闭检测后执行10亿次耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        test01();
        test02();
        test03();
    }
}
