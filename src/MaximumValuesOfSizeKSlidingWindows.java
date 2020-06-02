import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumValuesOfSizeKSlidingWindows {
    //way 1, use heap, lazy deletion
    //TODO: dijkstra's algrithm 用到lazy deletion 复习一下
    //T: nlogn 注意是logn, 这里的heap的大小是n, 不是k
    //S: O(n)
    public List<Integer> maxWindows(int[] array, int k) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(array.length, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return 0;
                }
                return a[0] > b[0] ? -1 : 1;
            }
        });
        int count = 0;
        for(int i = 0; i < array.length; i++){
            if(i < k - 1){
                maxHeap.offer(new int[]{array[i], i});
            }else{
                maxHeap.offer(new int[]{array[i], i});
                int[] max = maxHeap.peek();
                int maxIndex = max[1];
                //lazy deletion, 最大数不在当前的sliding window 范围内的时候，再delete
                while(!(maxIndex >= count && maxIndex <= count + k - 1)){
                    maxHeap.poll();
                    maxIndex = maxHeap.peek()[1];
                }
                res.add(array[maxIndex]);
                count++;
            }

        }
        return res;
    }
}
