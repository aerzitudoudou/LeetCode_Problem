public class SortArrayByParity_LC905 {
    //!!!sol1.1, my, O(n), O(1)
    public int[] sortArrayByParity(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l <= r){
            if(nums[l] % 2 == 0){
                l++;
            }else if(nums[r] % 2 == 1){
                r--;
            }else{
                swap(nums, l, r);
            }
        }

        return nums;
    }

    private void swap(int[] nums, int l, int r){
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    //!!!sol1.2, my, O(n), O(1)
    public int[] sortArrayByParity_1(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l <= r){
            while(l <= r && nums[l] % 2 == 0){
                l++;
            }
            while(l <= r && nums[r] % 2 == 1){
                r--;
            }
            if(l <= r){
                swap(nums, l, r);
            }
        }

        return nums;
    }
}
