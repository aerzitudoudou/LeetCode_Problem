import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK_LC560 {

    /*
k = 2
    1, 1, 1, -1, 1
presum  1  2  3, 2,  3

map: <presum, # of times of this presum>
 <0, 1> --> corner case for 1 element = k
 <1, 1>
 <2, 2>
 <3, 2>

count
for each presum, count += map.get(presum - k)

*/
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int pre = 0;
        for(int i = 0; i < nums.length; i++){
            int cur = nums[i] + pre;
            pre = cur;
            count += map.getOrDefault(cur - k, 0);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        return count;
    }
}
