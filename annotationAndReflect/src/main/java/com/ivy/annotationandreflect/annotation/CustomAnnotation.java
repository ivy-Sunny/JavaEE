package com.ivy.annotationandreflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CustomAnnotation
 * 自定义注解
 * @Author: ivy
 * @CreateTime: 2021-08-05
 */
public class CustomAnnotation {
    //注解可以显示赋值，如果没有默认值，我们必须给注解赋值
    @Annotation01(name = "ivySunny", age = 18,schools = {"哈哈"})
    public void test(){

    }
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Annotation01{
    //注解的参数: 参数类型 + 参数名();
    String name() default "";
    int age() default 18;
    int id() default -1;

    String[] schools();
}