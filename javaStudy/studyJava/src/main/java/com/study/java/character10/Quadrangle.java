package com.study.java.character10;

public class Quadrangle {
    //实例化保存四边形对象的数组对象
    private Quadrangle[] qtest = new Quadrangle[6];
    private int nextIndex = 0;

    public void draw(Quadrangle q) {
        if (nextIndex < qtest.length) {
            qtest[nextIndex] = q;
            System.out.println(nextIndex);
            nextIndex++;
            System.out.println(qtest[0].getClass().getName());

        }
    }

    public static void main(String args[]) {
        Quadrangle q = new Quadrangle();
        //向上转型:子类对象赋值给父类对象的变量
        q.draw(new Square());
        q.draw(new Parallelogram());
    }

}

class Square extends Quadrangle {
    public Square() {
        System.out.println("正方形");
    }
}

class Parallelogram extends Quadrangle {
    public Parallelogram() {
        System.out.println("平时四边形");
    }
}
