/*
*
*
* laicode
* 111. Random7 Using Random5
Medium
Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. Use random5() to implement random7().
*
*
* 5 ---> 扩容为25以后， 每一个数字出现概率是 1/25 从1 -- 20 的范围里， %7 后0到6出现等概率 = 3 / 25
*       %7[ 0,  1,  2,  3,  4,  5,  6 ]
*           0,  1,  2,  3,  4,  5,  6
*           7,  8,  9,  10, 11, 12, 13
*           14, 15, 16, 17, 18, 19, 20
*
*
*           21, 22, 23, 24
*
*
*
*
*
*
* */


import java.util.Random;

public class Random7UsingRandom5 {
    public int random7() {
        // write your solution here
        // you can use RandomFive.random5() for generating
        // 0 - 4 with equal probability.
        Random rand = new Random();

        int r = -1;
        do{
            r = 5 * rand.nextInt(5) + rand.nextInt(5);
        }while(r >= 21);

        return r % 7;
    }

}
