package com.study.java.character10;

/**
 * @author : zyw
 * @description :
 * @date : 2020/2/10
 * @modifier : zyw
 * @date : 2020/2/10
 */
public abstract class UseCase2 {
    abstract void testAbstract();

    public UseCase2() {//1.首先执行父类构造方法
        System.out.println("before testAbstract");
        testAbstract();//如果子类实现了抽象方法,将执行子类的testAbstract方法
        System.out.println("after testAbstract");
    }

    public static void main(String args[]) {
        new Sub2();
    }

}

class Sub2 extends UseCase2 {

    private int i = 1;//2.成员变量初始化
    @Override
    void testAbstract() {//执行子类实现的testAbstract方法
        System.out.println("Sub2 testAbstract");
    }

    public Sub2() {//4.执行子类的构造方法
        System.out.println(i);
    }
}
