import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum_UniquePairs_Lint587 {
    //sol1, my, O(nlogn), O(1)
     public int twoSum6(int[] nums, int target) {
         int res = 0;
         Arrays.sort(nums);
         int start = 0, end = nums.length - 1;
         while(start < end){
             if(nums[start] + nums[end] == target){
                 res++;
                 while(start < end && nums[start] == nums[start + 1]){
                     start++;
                 }
                 while(start < end && nums[end] == nums[end - 1]){
                     end--;
                 }
                 start++;
                 end--;
             }else if(nums[start] + nums[end] < target){
                 start++;
             }else{
                 end--;
             }
         }

         return res;
     }

    //sol2, https://www.jiuzhang.com/solution/two-sum-unique-pairs/, O(n), O(n)
    public int twoSum6_2(int[] nums, int target) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //initialize the map
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //count pairs
        for(int key : map.keySet()){
            int diff = target - key;
            //when equal, need to check if there are 2 equal element for sum instead of one
            //when not equal, only check the first half key < diff for the pairs for de-dup
            if(key == diff && map.get(key) > 1 || key < diff && map.containsKey(diff)){
                res++;
            }

        }

        return res;
    }
}
