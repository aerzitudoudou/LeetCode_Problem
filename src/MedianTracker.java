/*
*
*laicode
*
*113. Median Tracker
Medium
Given an unlimited flow of numbers, keep track of the median of all elements seen so far.

You will have to implement the following two methods for the class

read(int value) - read one value from the flow
median() - return the median at any time, return null if there is no value read so far
Examples

read(1), median is 1
read(2), median is 1.5
read(3), median is 2
read(10), median is 2.5
......
*
*
* -太极(物理意义 - 算法设计中不变的性质)：
* 1. max(maxheap) <= min(minheap)
* 2. size(maxheap) == size(minheap) || size(maxheap) = size(minheap) + 1
*
* -两仪(initialization):
* maxheap = minheap = {}
*
*
* -四象(算法设计去维持性质不变)
* if x <= max(maxheap)
*      maxheap.offer(x) ---> 维护第一个性质
*       if(maxheap  =  minheap + 2) --> 第二个性质
*               maxheap.pop
*               minheap.offer
*
*
* else
*      minheap.offer(x) ---> 第一个性质
*        if(maxheap + 1 = minheap)
*                minheap.pop        ---> 第二个性质
*                maxheap.offer
*
* TODO: 1. 总结一下不同基本数据类型以及他们的wrapper class之间相互的转化
* TODO: 2. 总结一下follow up 大数据memory 存不下怎么处理
* */

import java.util.Collections;
import java.util.PriorityQueue;


public class MedianTracker {
    //median 的数学意义，奇数个数的时候，就是sorted好了以后中间那个数。偶数个数时候，是sorted好了以后中间两个数的平均值
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    public MedianTracker() {
        // add new fields and complete the constructor if necessary.
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();

    }

    //T: logn
    //S: O(n)
    public void read(int value) {
        if(maxHeap.size() == 0 || value <= maxHeap.peek()){
            maxHeap.offer(value);
            if(maxHeap.size() == minHeap.size() + 2){
                minHeap.offer(maxHeap.poll());
            }
        }else{   //maxheap 不为空且value > maxheap.max
            minHeap.offer(value);
            if(minHeap.size() == maxHeap.size() + 1){
                maxHeap.offer(minHeap.poll());
            }
        }
    }


    public Double median() {
        if(maxHeap.size() == 0 && minHeap.size() == 0){
            return null;
        }
        if(maxHeap.size() > minHeap.size()){
            return maxHeap.peek().doubleValue();
        }

        double sum = maxHeap.peek() + minHeap.peek();
        return sum / 2;
    }
}
