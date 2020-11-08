package com.springboot.springboot.interview;

/**
 * Created by daixn on 2020/10/15 23:07
 */
public class Test {
    public static void main(String[] args) {
        Object[] strings = new String[10];
        int length = strings.length;
        sf4();
    }

    static void  sf4(){
        int f=0;
        int g=1;
        for (int i = 0; i <=15; i++) {
            System.out.print(f);
            f=f+g;
            g=f-g;
        }
    }
}
