package com.ivy.thread.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * UnsafeList
 * 线程不安全的集合
 * @Author: ivy
 * @CreateTime: 2021-08-04
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        System.out.println(list.size());
    }
}
