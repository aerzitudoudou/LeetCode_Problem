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
*
* Type                 Bit/Bytes               Range
* boolean                1 bit                 true or false
* char                16 bit / 2 bytes         0 to 65535
* byte                 8 bit/ 1 byte           -128 to 127
* short               16 bit / 2 bytes         -32768 to 32767
* int                 32 bit / 4 bytes         -2147483648 to 2147483647
* long                64 bit / 8 bytes         huge to huge
* float               32 bit / 4 bytes         varies
* double              64 bit / 8 bytes         varies
*
* 不同基本数据类型以及他们的wrapper class之间相互的转化:
* static 是class level method
* int : Integer
* Integer a
*
* Integer a = 5;
* Integer c = 5;
* a == c   -> true
*
*
* Integer a = 128
* Integer c = 128
* a == c --> false
* -------------------------------------------------------------
* Integer class cache integer object with value from -128 to 127
* -------------------------------------------------------------
*
* -------------------------------------------------------------
* Primitive/String to wrapper: Wrapper.valueOf(primitive)
* wrapper to primitive: WrapperObject.byte/short/int/float/doubleValue(); WrapperObject.toString()
* -------------------------------------------------------------
*
*
*
*
*
* int/String ---> Integer :autoboxing
* static Integer : Integer.valueOf(int i )
* static Integer : Integer.valueOf(String s)
* static Integer : Integer.valueOf(String s, int radix)
*
* Integer ---> primitive type:
* byte : a.byteValue()
* short: a.shortValue()
* int: a.intValue()
* long: a.longValue()
* float: a.floatValue();
* double: a.doubleValue()
*
*
*
*
*
*
* Integer ---> String
* static String: Integer.toBinaryString(int i)
* static String: Integer.toHexString(int i)
* static String: Integer.toOctalString(int i) 八进制
* static String: Integer.toString()
* String: a.toString()
*
*
*
*
* long : Long
*Long ---> primitive type
* a.byteValue()
* a.intValue()
* a.longValue()
* a.shortValue()
* a.floatValue()
*
*
* long/String ---> Long
* Long.valueOf(long l)
* Long.valueOf(String s)
*
*
*
*
* char : Character
* Character ---> char
*
*
*
*
* char ---> Character
* Character.valueOf(c)
*
*
*
* double : Double
*
*
* double/String---> Double
* Double.valueOf(double d)
* Double.valueOf(String s)
*
* Double ---> primitive type
* d.byteValue()
* d.doubleValue()
* d.intValue()
* d.shortValue()
* d.floatValue()
*
*
*
*
*
* float : Float
*
* float ---> Float
* Float.valueOf(float f)
* Float.valueOf(String s)
*
*
*
*
* Float ---> primitive type
* f.byteValue()
* f.floatValue()
* f.intValue()
* f.shortValue()
* f.longValue()
*
*
* boolean : Boolean
*
* -All wrapper class objects are immutable
* -always prefer primitive type to wrapper class(extra overhead)
* -int[] vs. Integer[]: there are totally different types and no auto conversion directly between them(写个helper撸一遍)
*
*
* -如果只有1G memory 但是数据量特别大怎么办? (hard disk 随便用)
*          small 50% elements                  ||                Larger 50% elements
*                                          1G memory
* disk(smaller sorted array)     maxHeap                    minHeap            disk(larger sorted array)
*       part 1                   part 2(500M)              part 3(500M)              part 4
*
* data structure properties
* 1. Max(part 1) <= Max(part 2 ) <= Min(part 3) <= Min(part 4)
* 2. size(part1) + size(part 2 ) ~= size(part 3) + size(part 4)
*
* Maintain the properties:
*
* In-memory process will be the same :
* 1. max(maxheap) <= min(minheap)
* 2. size(maxheap) == size(minheap) || size(maxheap) = size(minheap) + 1
* case 1: cur <= Max(part 2)
*           part 2. insert(cur)
*           if(part2 .size = part 3.size + 2)
*              part3. offer(part2.pop())
*
* case 2: cur > Min (part 3)
*           par3. insert(cur)
*           if(part3.size = part2.size + 1)
*               part2.offer(part3. pop)
*
* With hard disk,
* 1. If Max(part1) > Max(part2) || Min(part3) > Min(part4)
*    reorganize part1 and part2 || reorganize part3 and part4
*
*2. maxHeap is about to exceed 500M || minHeap is about to exceed 500M
*    reorganize part 1 and part2 let part 1 be around 250M || reorganize part 3 and part 4 let part 3 be around 250M
*     --2.1 merge part 1 and part 2（design class 3 and 5）
*     --2.2 put larger ~250M into part2
*     --2.3 put remaining data in a sorted array in part1
*
*
*TODO:看design class: memory 和disk 上的memory merge是怎么做的
*
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
