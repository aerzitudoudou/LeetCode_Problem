import java.util.Arrays;
import java.util.TreeMap;

public class MaximumProfitInJobScheduling_LC1235 {
    //!!!sol1.1, O(nlogn), O(n), from https://leetcode.com/problems/maximum-profit-in-job-scheduling/discuss/409009/JavaC%2B%2BPython-DP-Solution
    //dp[i] represents till time i what is the maximum profit
    //treemap可以保证endtime的order. 只记录endtime有值的这些点在dp里
    // e.g. job[start, end, profit]: [[1, 2, 5], [1, 3, 4], [1, 6, 4]], endtime 为1，5的点不用记录， 如果需要用到1就多往前看一步， 看0.
    //dp[i] 指可以取，或者不取以i为结尾的线段的profit，
    // 不取，放入上一个最大的profit: <cur endtime, lastValue of the treemap>
    // 取: dp[上一个比cur startime 小于等于的endtime 对应的profit] + profit[i]
    //max(取， 不取) 就是当前endtime的profit ， 放入dp treemap
    public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for(int i = 0; i < n; i++){
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        //sorted by endtime
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);


        //treemap: <endTime, profit> will be sorted by endTime
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);
        for(int[] job : jobs){

            int curStart = job[0];
            //take, find largest endtime that's <= curstart
            int prevProfit = map.floorEntry(curStart).getValue(); //running a binary search here!
            int withCurProfit = prevProfit + job[2];

            //not take
            int withoutCurProfit = map.lastEntry().getValue();

            int curProfit = Math.max(withoutCurProfit, withCurProfit);
            map.put(job[1], curProfit);

        }

        return map.lastEntry().getValue();

    }

    //so11.2, O(nlogn), O(n)， 不记录不取的情况，因为已经被preProfit记录住了
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for(int i = 0; i < n; i++){
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        //sorted by endtime
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);


        //treemap: <endTime, profit> will be sorted by endTime
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for(int[] job : jobs){

            int curStart = job[0];
            //largest endtime that's <= curstart
            int prevProfit = dp.floorEntry(curStart).getValue();
            int curProfit = prevProfit + job[2];
            if(curProfit > dp.lastEntry().getValue()){
                dp.put(job[1], curProfit);
            }
        }

        return dp.lastEntry().getValue();

    }


    //sol1.3, my, O(nlogn), O(n), manually run the binary search with dp[]. too much code, use treemap instead
    public int jobScheduling3(int[] startTime, int[] endTime, int[] profit) {
        //sort based on the end time
        /*
        [begin, end, profit]
        */
        int size =  startTime.length;
        int[][] job = new int[size][3];
        for(int i = 0; i < size; i++){
            job[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(job, (a, b) -> a[1] - b[1]);

        int[] dp = new int[size + 1];
        dp[0] = 0;

        for(int i = 1; i < size + 1; i++){
            //not take
            int withoutCurProfit = dp[i - 1];

            //take
            //find the first endtime <= start
            int start = job[i - 1][0];
            int j = findFirstLessOrEqual(job, start);
            //dp index = ary index + 1, j is array index
            int withCurProfit = dp[j + 1] + job[i - 1][2];

            dp[i] = Math.max(withoutCurProfit, withCurProfit);

        }


        return dp[size];
    }

    //find first element index where element <= target, if not exsit , return -1
    private int findFirstLessOrEqual(int[][] job, int target){
        int l = 0, r = job.length - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(job[mid][1] <= target){
                l = mid;
            }else{
                r = mid - 1;
            }

        }
        return job[l][1] <= target ? l : -1;
    }


}
