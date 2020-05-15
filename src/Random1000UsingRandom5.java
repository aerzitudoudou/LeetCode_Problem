import java.util.Random;

/*
*
* laicode
*
*
* 112. Random1000 Using Random5
Medium
Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. Use random5() to implement random1000()
*
*         4     3     2      1      0
*- 5进制： 5^4 + 5^3 +5^2 + 5^1 +  5^0    <= 2125   5进制每一位上随机生成一个数
*
* */
public class Random1000UsingRandom5 {
    public int random1000() {
        // Write your solution here.
        // you can use RandomFive.random5() for generating
        // 0 - 4 with equal probability.
        //5 ^ 5 = 3125
        Random rand = new Random();
        int r = 0;
        do{
            //第一次这里做错了，记得每一次要初始化r
            r = 0;
            for(int i = 0; i < 5; i++){
                //generate是乘（进制数）加（每一位数）
                //get每一位是除(进制数) 余（每一位数）
                r = r * 5 + rand.nextInt(5);
            }

        }while(r >= 1000);
        return r;
    }
}
