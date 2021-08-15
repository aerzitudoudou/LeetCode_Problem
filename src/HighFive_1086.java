import java.util.*;

public class HighFive_1086 {
    //sol1, my, O(nlog5), O(n* 5) = O(n)
    public int[][] highFive(int[][] items) {

        Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>();

        //T:n
        for(int i = 0; i < items.length; i++){
            int id = items[i][0];
            int score = items[i][1];

            PriorityQueue<Integer> minHeap = map.getOrDefault(id, new PriorityQueue<Integer>(5));
            //log5
            minHeap.offer(score);
            while(minHeap.size() > 5){
                minHeap.poll();
            }
            map.put(id, minHeap);
        }

        int[][] res = new int[map.size()][2];
        int i = 0;
        //T:n
        for(Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()){
            res[i][0] = entry.getKey();
            PriorityQueue<Integer> minHeap = entry.getValue();

            int sum = 0;
            //T:log5
            while(!minHeap.isEmpty()){
                sum += minHeap.poll();
            }
            res[i][1] = sum / 5;
            i++;
        }
        return res;
    }
}
