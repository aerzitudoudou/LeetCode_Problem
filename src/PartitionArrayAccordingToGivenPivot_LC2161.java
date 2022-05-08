public class PartitionArrayAccordingToGivenPivot_LC2161 {
    //sol1, my, O(n), O(n), [0, i) < k, [i,j] = k, (j, len - 1] >k
    public int[] pivotArray(int[] nums, int k) {
        int[] res = new int[nums.length];
        int l = 0, r = nums.length - 1;
        //<k
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < k){
                res[l] = nums[i];
                l++;
            }
        }
        //>K
        for(int i = nums.length - 1; i >= 0; i--){
            if(nums[i] > k){
                res[r] = nums[i];
                r--;
            }
        }

        //=K
        while(l <= r){
            res[l] = k;
            l++;
        }

        return res;
    }

}
