public class RunningSumOf1dArray_LC1480 {
    public int[] runningSum(int[] nums) {
        int count = 0, len = nums.length;
        int[] sum = new int[len];

        for(int i = 0; i < len; i++){
            sum[i] = count + nums[i];
            count = sum[i];
        }

        return sum;
    }
}
