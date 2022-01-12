import java.util.Deque;
import java.util.LinkedList;

public class ShortestSubarrayWithSumAtLeastK_LC862 {

    /*
    index:        i   j
    A:          1 2 3 4  5
    preSum S: 0 1 3 6 10 15
    Sj - Si >=k  => Sj >= Si + k

    for each j, in all i having Sj >= Si + k, find miminum j - i value

    Monotonic deque: maintains the index of St such that St is strictly increasing, and St inside queue are Si candidate

    whenever a St comes in to queue,
    - for Sk in the deque >= St , those cannot pair with St because St>=Sk+ k given k >= 1
                                 those also cannot pair with S after St because even it can satisfy S >= Sk+ k, index t is better than index k because t comes later to make the length of subarray shorter
     - therefore all Sk >= St can be removed

     - for those Sk in the deque with St - Sk >= k, update the res along the way with length t - k, and those Sk also can be removed before adding St in， because for the future S having S - Sk >= k, t is better index because than k to get the min length

     -therefore all Sk with St - Sk >= k can also be removed before St's added to the queue

     deque saving the index of St for calculating the length
     T: O(n) all elements offer and poll from deque only once
     S: O(n)


    */
    public int shortestSubarray(int[] nums, int k) {
        int len = nums.length;
        long[] preSum = new long[len + 1];
        for(int i = 1; i < len + 1; i++){
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int res = Integer.MAX_VALUE;

        Deque<Integer> deque = new LinkedList<>();

        for(int i = 0; i < len + 1; i++){
            while(!deque.isEmpty() && preSum[deque.peekLast()] >= preSum[i]){
                deque.pollLast();
            }
            while(!deque.isEmpty() && preSum[i] - preSum[deque.peekFirst()] >= k){
                res = Math.min(res, i - deque.peekFirst());
                deque.pollFirst();
            }
            deque.offerLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;



    }
}
