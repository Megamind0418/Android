package com.xm.oppo.utils;

/**
 * @author : STM
 * @description : 数字转大写字母工具类
 * @date : 2019/10/15/015
 * @modifier : STM
 * @date : 2019/10/15/015
 */
public class NumberToLetterUtil {
    public static String numberToLetter(int num) {
        if (num <= 0) {
            return null;
        }
        StringBuilder letter = new StringBuilder();
        num--;
        do {
            if (letter.length() > 0) {
                num--;
            }
            letter.insert(0, ((char) (num % 26 + (int) 'A')));
            num = (int) ((num - num % 26) / 26);
        } while (num > 0);
        return letter.toString();
    }
}
