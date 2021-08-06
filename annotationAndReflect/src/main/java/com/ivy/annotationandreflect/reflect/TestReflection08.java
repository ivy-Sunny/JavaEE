package com.ivy.annotationandreflect.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * TestReflection08
 * 通过反射获取泛型
 *
 * @Author: ivy
 * @CreateTime: 2021-08-06
 */
public class TestReflection08 {
    public void test01(Map<String, User> map, List<User> list) {

    }

    public Map<String, User> test02() {
        System.out.println("test02");
        return null;
    }

    /**
     * @param args
     * @throws NoSuchMethodException
     * ParameterizedType 参数化类型（即泛型）
     */
    public static void main(String[] args) throws NoSuchMethodException {

        /**
         * 方法的参数类型
         */
        Method method1 = TestReflection08.class.getMethod("test01", Map.class, List.class);
        Type[] genericParameterTypes = method1.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("#" + genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
        }

        System.out.println("--------------------------------->");
        /**
         * 方法的返回值类型
         */
        Method method2 = TestReflection08.class.getMethod("test02", null);
        Type genericReturnType = method2.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }
    }
}
