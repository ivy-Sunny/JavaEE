package com.ivy.thread;

/**
 * 静态代理模式
 */
public class StaticProxy {
}

interface Marry{
    /**
     * 人间四大喜事：
     *  1、久旱逢甘霖
     *  2、他乡遇故知
     *  3、洞房花烛夜
     *  4、金榜题名时
     */
    void HappyMarry();
}

/**
 * 真实角色，我自己结婚
 */
class Myself implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("ivySunny要结婚了，超开心");
    }
}

/**
 * 代理角色，帮助我结婚
 */
class WeddingCompany implements Marry{
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void before(){
        System.out.println("结婚之前，布置现场");
    }

    private void after(){
        System.out.println("结婚之时，收尾款");
    }
}