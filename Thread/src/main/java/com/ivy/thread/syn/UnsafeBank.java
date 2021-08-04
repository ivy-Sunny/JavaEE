package com.ivy.thread.syn;

/**
 * UnsafeBank
 * 不安全的取钱
 * 两个人同时取钱
 *
 * @Author: ivy
 * @CreateTime: 2021-08-04
 */
public class UnsafeBank {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100, "结婚基金");

        Drawing you = new Drawing(account, 50, "你");
        Drawing girlfriend = new Drawing(account, 100, "girlfriend");

        you.start();
        girlfriend.start();
    }
}

/**
 * 账户
 */
class Account {
    //余额
    int money;
    //卡名
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

/**
 * 银行：模拟取款
 */
class Drawing extends Thread {
    //账户
    Account account;
    //取了多少钱
    int drawingMoney;
    //现在手里多少钱
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        //判断有没有钱
        if (account.money - drawingMoney < 0) {
            System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
            return;
        }
        //卡内余额 = 余额 - 取得数目
        account.money = account.money - drawingMoney;
        //手里的钱
        nowMoney = nowMoney + drawingMoney;
        System.out.println(account.name + "余额为：" + account.money);
        System.out.println(this.getName() + "手里的钱：" + nowMoney);
    }
}