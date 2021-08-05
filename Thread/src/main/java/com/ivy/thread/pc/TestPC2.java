package com.ivy.thread.pc;

/**
 * TestPC2
 * 测试生产者消费者模型2: ---> 信号灯法，标识位解决
 *
 * @Author: ivy
 * @CreateTime: 2021-08-05
 */
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Actor(tv).start();
        new Audience(tv).start();
    }
}

//生产者--->演员
class Actor extends Thread {
    TV tv;

    public Actor(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.perform("快乐大本营播放中");
            } else {
                this.tv.perform("抖音:记录美好生活");
            }
        }
    }
}

//消费者--->观众
class Audience extends Thread {
    TV tv;

    public Audience(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

//产品--->节目
class TV {

    //演员表演，观众等待 T
    //观众观看，演员等待 F
    private String program;
    private boolean flag = true;

    //表演
    public synchronized void perform(String program) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了" + program);
        this.notifyAll();
        this.program = program;
        this.flag = !this.flag;
    }

    //观看
    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("观看了:" + this.program);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //通知演员表演
            this.notifyAll();
            this.flag = !this.flag;
        }
    }
}
