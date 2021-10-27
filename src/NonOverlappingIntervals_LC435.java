import java.util.Arrays;

public class NonOverlappingIntervals_LC435 {
    //!!sol1, greedy , from acwing https://www.acwing.com/video/1826/ , O(nlogn), O(1)
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int r = intervals[0][1];
        int res = 1;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >=  r){
                res++;
                r = intervals[i][1];
            }
        }

        return intervals.length - res;
    }
}
