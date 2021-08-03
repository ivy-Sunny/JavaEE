package com.ivy.thread;

/**
 * 静态代理模式
 *  总结：
 *      真实对象和代理对象都要实现同一个接口
 *      代理对象去代理真实角色
 *  优点：
 *      代理对象可以做很多真实对象做不了的事情
 *      真实对象专注做自己的事情
 */
public class StaticProxy {
    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new Myself());
        weddingCompany.HappyMarry();

        new WeddingCompany(() -> System.out.println("我（ivySunny）要结婚了，超开心")).HappyMarry();
    }
}

interface Marry{
    /**
     * 人间四大喜事：S
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
        System.out.println("ivySunny（我）要结婚了，超开心");
    }
}

/**
 * 代理角色，帮助我结婚
 */
class WeddingCompany implements Marry{

    //代理真实目标角色
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
        System.out.println("结婚之后，收尾款");
    }
}