package com.ivy.thread;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TestJUC
 * 测试JUC安全类型的集合
 * @Author: ivy
 * @CreateTime: 2021-08-04
 */
public class TestJUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
