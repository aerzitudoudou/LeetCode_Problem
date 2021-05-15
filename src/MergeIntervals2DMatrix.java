import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode 208
public class MergeIntervals2DMatrix {
    //!!!!!!how to sort 2-d array, list to array java implementation
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] last = null;//semantic meaning: cur last item in the result list
        for(int[] cur : intervals){
            if(last == null || cur[0] > last[1]){//when to replace last by adding a new one
                list.add(cur);
                last = cur;
            }else{
                last[1] = Math.max(cur[1], last[1]);//when to only update last
            }
        }

        return list.toArray(new int[list.size()][2]);

    }

}
