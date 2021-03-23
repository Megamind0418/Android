package com.study.java.character11;

/**
 * @author : zyw
 * @description :
 * @date : 2020/2/11
 * @modifier : zyw
 * @date : 2020/2/11
 */
public class OuterClass {
    interClass in = new interClass();

    class interClass {
        public interClass() {
        }

        public void inf() {
            System.out.println("inf");
        }

        int y = 0;
    }

    public interClass doit() {
        in.y = 4;
        return new interClass();
    }

    public static void main(String args[]) {
        OuterClass out = new OuterClass();
        OuterClass.interClass in = out.doit();
        OuterClass.interClass in2 = out.new interClass();
    }
}
