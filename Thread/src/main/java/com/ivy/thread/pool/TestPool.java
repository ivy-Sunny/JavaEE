package com.ivy.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TestPool
 * 测试线程池
 *
 * @Author: ivy
 * @CreateTime: 2021-08-05
 */
public class TestPool {
    public static void main(String[] args) {
        //1、创建服务，创建线程池
        //newFixedThreadpool 参数为：线程池大小
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
