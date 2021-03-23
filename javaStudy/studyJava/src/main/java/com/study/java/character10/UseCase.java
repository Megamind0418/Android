package com.study.java.character10;

/**
 * @author : zyw
 * @description :
 * @date : 2020/2/10
 * @modifier : zyw
 * @date : 2020/2/10
 * 创建父类,父类创建两个方法,在子类中覆盖第二个方法
 */
public class UseCase {
    public void doSomething() {
        System.out.println("doSomething");
    }

    public void doAnything() {
        System.out.println("doAnything");
    }

    public static void main(String args[]) {
//        为子类创建一个对象,向上转型到基类
        UseCase u = new Sub();
        u.doSomething();
//        并调用这个方法
        u.doAnything();
    }
}


class Sub extends UseCase {
    @Override
    public void doAnything() {
        System.out.println("doAnythingSub");
    }
}
