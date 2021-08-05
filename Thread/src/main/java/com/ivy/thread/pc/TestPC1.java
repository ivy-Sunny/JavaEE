package com.ivy.thread.pc;

/**
 * TestPC
 * 测试生产者消费者模型1: ---> 利用缓冲区解决（管程法）
 *
 * @Author: ivy
 * @CreateTime: 2021-08-05
 */
public class TestPC1 {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        Productor productor = new Productor(container);
        Consumer consumer = new Consumer(container);
        productor.start();
        consumer.start();
    }
}

/**
 * 生产者
 */
class Productor extends Thread {
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                Thread.sleep(10);
                System.out.println("生产了一只鸡:" + count);
                container.push(new Chicken(count++));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                Chicken chicken = container.pop();
                System.out.println("消费了一只鸡:" + chicken.id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 产品
 */
class Chicken {
    //编号
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

/**
 * 缓冲区
 */
class SynContainer {
    //容器
    Chicken[] chickens = new Chicken[10];
    //容器计数器
    int count = 0;

    //生产者放入产品
    public synchronized void push(Chicken chicken) {
        //如果容器满了，就需要等待消费者消费
        if (count == chickens.length - 1) {
            //生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        //如果没有满，我们就需要丢入产品
        chickens[count] = chicken;
        count++;
        //通知消费者消费
        this.notifyAll();
    }

    //消费者消费产品
    public synchronized Chicken pop() {
        //判断能否消费
        if (count == 0) {
            //消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        //如果可以消费
        Chicken chicken = chickens[count];
        count--;
        this.notifyAll();
        return chicken;
    }
}