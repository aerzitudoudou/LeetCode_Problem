public class TwoSumII_InputArrayIsSorted_LC167 {
    //sol1, my, O(n), O(1)
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int[] res = new int[2];
        while(i < j){
            if(nums[i] + nums[j] == target){
                res[0] = i + 1;
                res[1] = j + 1;
                return res;
            }
            if(nums[i] + nums[j] > target){
                j--;
            }else i++;
        }

        return res;

    }
}
