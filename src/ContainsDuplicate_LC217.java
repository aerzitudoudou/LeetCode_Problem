import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate_LC217 {
    //sol1, O(n), O(n)
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            if(!set.contains(i)){
                set.add(i);
            }else{
                return true;
            }
        }
        return false;
    }

    //sol2, O(nlogn), O(1)
    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]) return true;
        }

        return false;
    }
}
