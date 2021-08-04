package com.ivy.thread;

/**
 * TestStop
 * 测试Stop
 * 1、建议线程使用标识位 ---> 设置一个自定义标识位
 * 2、建议线程正常停止 --->
 * 3、不要使用stop或者destroy等过时或者JDK不建议使用的方法
 *
 * @Author: ivy
 * @CreateTime: 2021-08-04
 */
public class TestStop implements Runnable {
    //1'设置一个标识位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run...Thread:" + i++);
        }
    }

    //2'设置一个公开的方法停止线程，转换标识位
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) throws InterruptedException {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main:" + i);
            if (i == 900) {
                testStop.stop();
                System.out.println("线程停止");
            }
        }
    }
}
