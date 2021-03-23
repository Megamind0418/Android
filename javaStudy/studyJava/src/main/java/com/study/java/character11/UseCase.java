package com.study.java.character11;

/**
 * @author : zyw
 * @description :
 * @date : 2020/2/11
 * @modifier : zyw
 * @date : 2020/2/11
 */

interface OuterInterface {
    public int getValue();
}

public class UseCase {
    public int doit(final String x) {
         return new OuterInterface() {
            private int i = 0;

            public int getValue() {
                System.out.println("getValue");
                return i;
            }
        }.getValue();
    }

    public static void main(String args[]) {
        UseCase useCase = new UseCase();
        useCase.doit("2");
    }

}


