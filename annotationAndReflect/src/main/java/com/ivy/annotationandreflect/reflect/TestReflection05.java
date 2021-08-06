package com.ivy.annotationandreflect.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TestReflection05
 *
 * @Author: ivy
 * @CreateTime: 2021-08-06
 */
public class TestReflection05 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class c1 = Class.forName("com.ivy.annotationandreflect.reflect.User");

        //获得包名 + 类名
        System.out.println(c1.getName());
        //获得类名
        System.out.println(c1.getSimpleName());

        /**
         * 获得类的属性
         * getFields() 获得public属性
         * getDeclaredFields() 获得全部的属性
         */
        System.out.println("------------------------------------------>");
        Field[] fields = c1.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field[] declaredFields = c1.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        /**
         * 获得类的方法
         * getMethods() 获得public方法
         * getDeclaredMethods() 获得全部的方法
         */
        System.out.println("------------------------------------------>");
        Method[] declaredMethods = c1.getDeclaredMethods();
        Method[] methods = c1.getMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        for (Method method : methods) {
            System.out.println(method);
        }
        /**
         * 获得指定的方法
         * 要考虑到重载
         */
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        /**
         * 获得指定的构造器
         */
        System.out.println("------------------------------------------>");
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
    }
}
