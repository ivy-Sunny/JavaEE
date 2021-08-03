package com.ivy.thread;

/**
 * lambda表达式
 */
public class LambdaShow {
    public static void main(String[] args) {
        ILike like1 = new Like1();
        like1.lambda();

        ILike like2 = new Like2();
        like2.lambda();

        /**
         * 4.局部内部类
         */
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("i like lambda4");
            }
        }

        ILike like3 = new Like3();
        like3.lambda();

        /**
         * 5.匿名内部类
         */
        ILike like4 = new ILike() {
            @Override
            public void lambda() {
                System.out.println("i like lambda4");
            }
        };
        like4.lambda();

        /**
         * 6.lambda简化
         */
        ILike like5 = () -> {
            System.out.println("i like lambda5");
        };

    }

    /**
     * 3.静态内部类
     */
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("i like lambda2");
        }
    }
}

/**
 * 1.定义一个函数式接口
 */
interface ILike {
    void lambda();
}

/**
 * 2.实现类
 */

class Like1 implements ILike {
    @Override
    public void lambda() {
        System.out.println("i like lambda1");
    }
}