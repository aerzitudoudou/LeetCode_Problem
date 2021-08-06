public class RangeSumQuery_Immutable_LC303 {
    //way1, my, presum
    int[] preSum;
    //O(n)
    public RangeSumQuery_Immutable_LC303(int[] nums) {
        preSum = new int[nums.length + 1];
        for(int i = 1; i <= nums.length; i++){
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

    }
    //O(1)
    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }

}
