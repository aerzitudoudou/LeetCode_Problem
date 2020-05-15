import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* 110. Generalized Reservoir Sampling
Medium
Consider an unlimited flow of data elements. How do you sample k element from this flow, such that at any point during the processing of the flow, you can return a random set of k elements from the n elements read so far.

Assumptions

k >= 1
You will implement two methods for a sampling class:

read(int value) - read one number from the flow
sample() - return at any time the k samples as a list, return the list of all values read when the number of values read so fas <= k.
You may need to add more fields for the class.
*
*
*-理解reservoir sampling 概率证明过程
*
*
*
* */


public class GeneralizedReservoirSampling {
    private final int k;
    private int counter;
    private List<Integer> sample;
    private Random rand;
    public GeneralizedReservoirSampling(int k) {
        this.k = k;
        this.counter = 0;
        this.sample = new ArrayList<>();
        this.rand = new Random();
    }

    //e.g. k = 100

    public void read(int value) {
        //如果counter < 100 i.e. counter 在[0, 99]之间
        if(counter < k){
            sample.add(value);
        }else{
            //0到99之间的100个数已经算好
            //从0到100之间选一个
            //counter == k == 100
            //rand(101) 是在[0,100]之间选一个， 所以下面是counter + 1
            int j = rand.nextInt(counter + 1);
            //选出来的j位置如果再sample区间里， 在j位置放上当前value， 可以保证每一个元素出现在sample里的概率是k / n
            /*
            * 证明：
            * Let s = {s1, s2, s3, .......sk}
            * prove Pr(ai 属于s) = k / n, n是所有element的个数
            * Pr(ai 属于 s) = k / i "第i个数出现在sample里的概率" * [1 - （1 / i + 1)] "1 - 第i + 1个数出现在刚刚第i个数在sample里的位置的概率 = 第i + i 个数不出现在刚刚i在sample里位置的概率" * [1 - (1 / i + 2)] * .....*[1 - (1 / n)]
            * = k/i * i/i+1 * i+1/i+2 *....n-1/n = k/n
            *
            *
            *
            *
            *
            * */
            if(j < k){
                sample.set(j, value);
            }
        }
        counter++;
    }

    public List<Integer> sample() {
        return sample;
    }
}
