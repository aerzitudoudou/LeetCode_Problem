import java.util.*;

/*
* laicode 193
*
* 193. Kth Smallest With Only 3, 5, 7 As Factors
Medium
Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and y > 0 and z > 0, x, y, z are all integers.

Assumptions

K >= 1
Examples

the smallest is 3 * 5 * 7 = 105
the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
the 5th smallest is 3 ^ 3 * 5 * 7 = 945
*
*
*
* */
public class KthSmallestWithOnly357AsFactors {
    //T: klog(k)
    //S: O(k)
    public long kth(int k) {
        //data structure: pq, set(deduplication)
        PriorityQueue<Long> pq = new PriorityQueue<>(k);
        Set<Long> set = new HashSet<>();
        /*
        Offer 这里涉及到的java conversion:
                               size       1byte    2byte   4byte    8byte    4byte     8 byte
        1. primitive widening conversion   byte--> short --> int --> long --> float --> double   --> 是精度(widening)越来越高的方向。低精度转换到高精度是implicit 转化。不需要加（）
        2. reference widening conversion: A widening reference conversion exists from any reference type S to any reference type T, provided S is a subtype (§4.10) of T.
            父类是精度越来越高的方向
           Widening reference conversions never require a special action at run time and therefore never throw an exception at run time. 不需要加括号

           所以这里offer后面不能直接跟3 * 5 * 7因为://TODO: 更新once get 答案


        * */

        pq.offer(Long.valueOf(3 * 5 * 7));
        set.add(Long.valueOf(3 * 5 * 7));
        return bfs(pq, set, k);




    }

    private long bfs(PriorityQueue<Long> pq, Set<Long> set, int k){
        int[] ary = {3, 5, 7};
        for(int i = 0; i < k - 1; i++){
            //expand
            long tmp = pq.poll();
            //generate
            for(int a = 0; a < 3; a++){
                //generate
                if(!set.contains(tmp * ary[a])){
                    pq.offer(tmp * ary[a]);
                    set.add(tmp * ary[a]);
                }
            }
        }
        return pq.peek();
    }
}
