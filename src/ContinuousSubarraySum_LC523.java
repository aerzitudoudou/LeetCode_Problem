import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum_LC523 {
    /*
   ary:       23 2  4  6  7
   presum:    0  23 25 29 32 42
   to have sum of ary[i,j] multiple of k. need presum[j] - presum[i] mulitple of k

   presum[j] = m * k + modj
   presum[i] = n * k + modi

   [m * k + modj] - [n * k + modi need to be a mutiple of k

   (m-  n) * k + modj - modi is a multiple of k

   i.e. modj = mod i   && j - i > 1


   */
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        //<mod, index>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for(int i = 1; i < sum.length; i++){
            sum[i] = nums[i - 1] + sum[i - 1];
            Integer prev = map.putIfAbsent(sum[i] % k, i);
            if(prev != null && i - prev > 1) return true;
        }
        return false;



    }
}
