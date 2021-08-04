package com.ivy.thread;

/**
 * TestDeamon
 * 守护进程
 *
 * @Author: ivy
 * @CreateTime: 2021-08-04
 */

public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        IvySunny ivySunny = new IvySunny();
        Thread thread = new Thread(god);
        //默认是false，表示是用户线程，正常的线程都是用户线程
        thread.setDaemon(true);
        //上帝守护线程启动
        thread.start();

        //ivySunny用户线程启动
        new Thread(ivySunny).start();
    }
}

/**
 * 我
 */
class IvySunny implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("一生都要开心的活着");
        }
        System.out.println("goodbye! world");
    }
}

/**
 * 上帝
 */
class God implements Runnable {

    @Override
    public void run() {
        while (true) {

            System.out.println("上帝守护着你...");
        }
    }
}