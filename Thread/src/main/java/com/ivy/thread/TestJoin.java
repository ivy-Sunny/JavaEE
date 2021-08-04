package com.ivy.thread;

/**
 * TestJoin
 *
 * @Author: ivy
 * @CreateTime: 2021-08-04
 */
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程vip来了:" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //启动我们的线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        //主线程
        for (int i = 0; i < 200; i++) {
            if (i == 50) {
                thread.join();
            }
            System.out.println("main:" + i);
        }
    }
}
