package com.ivy.annotationandreflect.reflect;

/**
 * TestReflection02
 * 测试Class类的创建方式有哪些
 *
 * @Author: ivy
 * @CreateTime: 2021-08-05
 */
public class TestReflection02 {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("这个人是：" + person.name);

        //方式1：通过 实例获得
        Class<? extends Person> aClass1 = person.getClass();

        //方式2：通过 Class.forName获得
        Class<?> aClass2 = Class.forName("com.ivy.annotationandreflect.reflect.Student");

        //方式3：通过 类名.class获得
        Class<Student> aClass3 = Student.class;

        //方式4：内置基本类型的包装类都有一个Type属性
        Class<Integer> type = Integer.TYPE;

        System.out.println(aClass1.hashCode());
        System.out.println(aClass2.hashCode());
        System.out.println(aClass3.hashCode());
    }
}

class Person {
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {
    public Student() {
        this.name = "学生";
    }
}

class Teacher extends Person {
    public Teacher() {
        this.name = "老师";
    }
}