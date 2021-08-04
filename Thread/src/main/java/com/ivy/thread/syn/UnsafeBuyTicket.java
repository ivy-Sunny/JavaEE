package com.ivy.thread.syn;

/**
 * UnsafeBuyTicket
 * 不安全的买票
 * 线程不安全
 *
 * @Author: ivy
 * @CreateTime: 2021-08-04
 */
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();
        new Thread(station, "苦逼的我").start();
        new Thread(station, "牛逼的你们").start();
        new Thread(station, "可恶的黄牛").start();
    }
}

class BuyTicket implements Runnable {
    //票
    private int ticketNums = 20;
    //外部停止方式
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //synchronized 同步方法，锁的是this，当前对象
    private synchronized void buy() throws InterruptedException {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        //模拟延时
        Thread.sleep(250);
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);

    }
}
