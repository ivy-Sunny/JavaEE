package com.ivy.annotationandreflect.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.*;

/**
 * TestAnnotation
 * 测试元注解
 *
 * @Author: ivy
 * @CreateTime: 2021-08-05
 */
public class TestAnnotation extends Object {
    @Override
    public String toString() {
        return super.toString();
    }

    @MyAnnotation
    public void test() {

    }
}

/**
 * 定义一个注解
 * Target: 表示我们的注解可以用在哪些地方
 * Retention: 表示我们的注解在什么地方有效（Runtime > Class > Sources）
 * Documented: 表示是否将我们的注解生成在JavaDoc中
 * Inherited: 表示子类可以继承父类的注解
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RUNTIME)
@Documented
@Inherited
@interface MyAnnotation {

}
