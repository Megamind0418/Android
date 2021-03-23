package com.study.java.character11;

/**
 * @author : zyw
 * @description :
 * @date : 2020/2/11
 * @modifier : zyw
 * @date : 2020/2/11
 */

interface OutInterface2 {

}
public class OuterClass2 {
    public OutInterface2 doit(final String x) {
        class InnerClass2 implements OutInterface2 {
            public InnerClass2(String s) {
                s = x;
                System.out.println(s);
            }
        }
        return new InnerClass2("doit");
    }
}
