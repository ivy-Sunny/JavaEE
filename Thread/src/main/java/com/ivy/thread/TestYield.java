package com.ivy.thread;

/**
 * TestYield
 * 礼让线程
 * 礼让不一定成功，看CPU调度
 *
 * @Author: ivy
 * @CreateTime: 2021-08-04
 */
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield, "A").start();
        new Thread(myYield, "B").start();
    }
}

class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程停止执行");
    }
}