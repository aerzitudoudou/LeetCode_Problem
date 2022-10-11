import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements_LC347 {
    //!!!sol1, my, use minheap, O(nlogk), O(k)
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>(); //<num, count>
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) ->{
        //     return e1.getValue() - e2.getValue();
        // });

        //heap store the number itself depending on its count in map
        //no need to store Map.Entry since map is visible throughout this method, just need to store the number itself
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((i1, i2) ->{
            return map.get(i1) - map.get(i2);
        });

        for(int key : map.keySet()){
            minHeap.offer(key);
            if(minHeap.size() > k) minHeap.poll();
        }

        for(int i = 0; i < k; i++){
            res[i] = minHeap.poll();
        }
        return res;



    }
}
