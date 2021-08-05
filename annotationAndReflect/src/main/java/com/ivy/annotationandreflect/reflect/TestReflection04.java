package com.ivy.annotationandreflect.reflect;

/**
 * TestReflection04
 * 测试 类什么时候初始化
 * @Author: ivy
 * @CreateTime: 2021-08-05
 */
public class TestReflection04 {
    static {
        System.out.println("Main类被加载");
    }
}

class Father{
    static{
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int M = 1;
}