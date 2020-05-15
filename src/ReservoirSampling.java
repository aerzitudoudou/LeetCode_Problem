/*
*laicode
*109. Reservoir Sampling
Medium
Consider an unlimited flow of data elements. How do you sample one element from this flow, such that at any point during the processing of the flow, you can return a random element from the n elements read so far.

You will implement two methods for a sampling class:

read(int value) - read one number from the flow
sample() - return at any time the sample, if n values have been read, the probability of returning any one of the n values is 1/n, return null(Java)/INT_MIN(C++) if there is no value read so far
You may need to add more fields for the class.
*
* */

import java.util.Random;
//到counter为止rand(counter) == 0 的概率就是1/counter
public class ReservoirSampling {
    int count;
    Integer sample;
    public ReservoirSampling() {
        count = 0;
        sample = null;

    }

    public void read(int value) {
        count++;
        Random rand = new Random();
        //以 1/ counter 的概率把sample换成当前的value
        if(rand.nextInt(count) == 0){
            sample = value;
        }
    }

    public Integer sample() {
        // Write your implementation here.
        return sample;
    }
}
