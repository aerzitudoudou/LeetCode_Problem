import java.util.HashMap;
import java.util.Map;

public class TwoSum_DifferenceEqualsToTarget_Lint610 {
    //sol1, my, O(n), O(n)
     public int[] twoSum7(int[] nums, int target) {
         int l = nums.length;

         if(target == 0){
             for(int i = 1; i < l; i++){
                 if(nums[i] == nums[i - 1]) return new int[]{nums[i], nums[i]};
             }
             return null;
         }
         int[] sum = new int[l];
         for(int i = 0; i < l; i++){
             sum[i] = nums[i] + target;
         }

         int i = 0, j = 0;
         while(i < l && j < l){
             if(nums[i] == sum[j]){
                 return i > j ? new int[]{nums[j], nums[i]} : new int[]{nums[i], nums[j]};
             }
             else if(nums[i] < sum[j]) i++;
             else j++;
         }

         return null;

     }

    //!!!sol2, sliding window, from labuladong: https://blog.csdn.net/fdl123456/article/details/105697625, O(n), O(1)
    public int[] twoSum7_1(int[] nums, int target) {
        target = Math.abs(target);
        int l = 0, r = 0;
        int len = nums.length;
        while(r < len){
            r++;
            // if(nums[r] - nums[l] == target && r != l) return new int[]{nums[l], nums[r]};
            // else if (nums[r] - nums[l] < target) continue;

            while(nums[r] - nums[l] > target) l++;
            if(nums[r] - nums[l] == target && r != l) return new int[]{nums[l], nums[r]};
        }

        return null;
    }

    /*滑动窗口模版*/
    void slidingWindow(String s, String t){
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for(char c : s.toCharArray()){
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        while(right < s.length()){
            //c 是移入窗口的字符
            char c = s.charAt(right);
            //右移窗口
            right++;
            //....
            //进行窗口内数据的一系列更新

           // while(window needs shrink){
                //d是将移出窗口的字符
                char d = s.charAt(left);
                //左移窗口
                left++;
                //....
                //进行窗口内数据的一系列更新
           // }

        }
    }

}
