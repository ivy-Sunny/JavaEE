package com.ivy.annotationandreflect.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TestReflection06
 * 通过反射，动态的创建对象
 *
 * @Author: ivy
 * @CreateTime: 2021-08-06
 */
public class TestReflection06 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        //获得Class对象
        Class<?> c1 = Class.forName("com.ivy.annotationandreflect.reflect.User");

        /**
         * 构造一个对象
         * 本质是调用了类的无参构造器
         */
        User user1 = (User) c1.newInstance();
        System.out.println(user1);
        //通过有参构造器创建对象
        Constructor<?> declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User user2 = (User) declaredConstructor.newInstance("ivySunny", 1, 18);
        System.out.println(user2);

        /**
         * 通过反射调用方法
         * invoke: 激活的意思
         *      （对象，"方法的值"）
         */
        User user3 = (User) c1.newInstance();
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user3, "ivySunny");
        System.out.println(user3);

        /**
         * 通过反射操作属性
         * setAccessible(true): 关闭安全检查
         *      不能直接操作私有属性或者方法，我们需要关闭程序的安全检测
         */
        User user4 = (User) c1.newInstance();
        Field name = c1.getDeclaredField("name");
        //关闭安全检查
        name.setAccessible(true);
        name.set(user4, "余晖");
        System.out.println(user4);
    }
}
