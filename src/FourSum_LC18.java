import java.util.*;

public class FourSum_LC18 {
    //!!!sol1, from acwing, https://www.acwing.com/video/1327/, O(n^3), O(1)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            for(int j = i + 1; j < nums.length; j++){
                if(j > i + 1 && nums[j] == nums[j - 1]) continue;
                int l = j + 1, r = nums.length - 1;
                int curTarget = target - nums[i] - nums[j];

                while(l < r){
                    int tmp = nums[l] +  nums[r];
                    if(tmp == curTarget){
                        Integer[] ary = {nums[i], nums[j], nums[l], nums[r]};
                        res.add(Arrays.asList(ary));
                        l++;
                        r--;
                        while(l < r && nums[l] == nums[l - 1]) l++;
                        while(l < r && nums[r] == nums[r + 1]) r--;
                    }else if(tmp < curTarget) l++;
                    else r--;
                }
            }
        }

        return res;
    }
}
