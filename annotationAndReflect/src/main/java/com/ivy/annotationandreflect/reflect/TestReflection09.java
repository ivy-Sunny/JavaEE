package com.ivy.annotationandreflect.reflect;

import java.lang.annotation.*;
import java.lang.reflect.Field;

import static java.lang.annotation.RetentionPolicy.*;

/**
 * TestReflection09
 * 测试反射操作注解
 *
 * @Author: ivy
 * @CreateTime: 2021-08-06
 */
public class TestReflection09 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> c1 = Class.forName("com.ivy.annotationandreflect.reflect.Student2");
        //通过反射获得注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        //获得注解中value的值
        TableSunny tableSunny = c1.getAnnotation(TableSunny.class);
        String value = tableSunny.value();
        System.out.println(value);

        //获得类指定的注解
        Field name = c1.getDeclaredField("name");
        FieldSunny fieldSunny = name.getAnnotation(FieldSunny.class);
        System.out.println(fieldSunny.columnName());
        System.out.println(fieldSunny.type());
        System.out.println(fieldSunny.length());
    }
}

@TableSunny("db_student")
class Student2 {
    @FieldSunny(columnName = "db_id", type = "int",length = 10)
    private int id;
    @FieldSunny(columnName = "db_age", type = "int",length = 10)
    private int age;
    @FieldSunny(columnName = "db_name", type = "String",length = 10)
    private String name;

    public Student2() {
    }

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 类名的注解
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
@interface TableSunny {
    String value();
}

/**
 * 属性的注解
 */
@Target(ElementType.FIELD)
@Retention(RUNTIME)
@interface FieldSunny {
    String columnName();

    String type();

    int length();
}
