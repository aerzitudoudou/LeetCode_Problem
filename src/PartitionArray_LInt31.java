public class PartitionArray_LInt31 {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    //sol1, my, O(n), O(1)
    public int partitionArray(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        // int count = 0;
        // for(int i = 0; i < nums.length; i++){
        //     if(nums[i] >= k) count++;
        // }

        // return nums.length - count;

        int i = 0, j = nums.length - 1;
        while(i <= j){
            if(nums[i] < k && nums[j] >= k){
                i++;
                j--;
            }else if(nums[i] >= k && nums[j] < k){
                swap(nums, i, j);
                i++;
                j--;
            }else if(nums[i] < k){
                i++;
            }else{
                j--;
            }
        }

        return j + 1;
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
