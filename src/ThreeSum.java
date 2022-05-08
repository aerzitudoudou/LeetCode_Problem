import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> allTriples(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(array);
        if(array == null || array.length <= 2){
            return res;
        }


        for(int c = 0; c < array.length - 2; c++){
            if(c > 0 && array[c - 1] == array[c]){
                continue;
            }
            int i = c + 1, j = array.length - 1;
            while(i < j){
                if(array[i] + array[j] == target - array[c]){
                    res.add(Arrays.asList(array[c], array[i], array[j]));
                    break;

                }else if(array[i] + array[j] < target - array[c]){
                    while(i <= array.length - 2 && array[i] == array[i + 1]){
                        i++;
                    }
                    i++;
                }else{
                    while(j >= 2 && array[j] == array[j - 1]){
                        j--;
                    }
                    j--;
                }
            }
        }
        return res;


    }


    //sol1, from acwing, T: O(nlogn + n^2) = O(n^2)， S: worst case: O(n) average:O(logn)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++){
            //de-dup first pointer
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1;
            int k = nums.length - 1;
            while(j < k){
                if(nums[i] + nums[j] + nums[k] == 0){
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    //de-dup j and k
                    while(j < nums.length && nums[j] == nums[j - 1]) j++;
                    while(k >= 0 && nums[k] == nums[k + 1]) k--;
                }else if(nums[i] + nums[j] + nums[k] > 0){
                    k--;
                }else{
                    j++;
                }
            }
        }

        return res;
    }
}
