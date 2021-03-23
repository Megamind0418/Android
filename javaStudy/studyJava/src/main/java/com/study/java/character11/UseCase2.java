package com.study.java.character11;

/**
 * @author : zyw
 * @description :
 * @date : 2020/2/11
 * @modifier : zyw
 * @date : 2020/2/11
 */
public class UseCase2 {
    static class a {
        void f() {
            System.out.println("静态内部类");
        }
    }
    public static void main(String args[]) {
        UseCase2.a a = new a();
        a.f();
    }
}
