import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    //myway T: nlogn S: O(n)
    public int lastStoneWeight(int[] stones) {
        int res = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(stones.length, Collections.reverseOrder());
        for(int a : stones){ //O(n)
            maxHeap.offer(a);
        }
        while(maxHeap.size() > 1){ //nlogn
            int max1 = maxHeap.poll();
            int max2 = maxHeap.poll();
            if(max1 == max2) continue;
            else{
                maxHeap.offer(Math.abs(max1 - max2));
            }
        }
        if(maxHeap.size() == 0){
            return 0;
        }
        return maxHeap.poll();
    }
    //TODO：理解bucket sort 的做法？

}
