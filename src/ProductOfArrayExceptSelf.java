public class ProductOfArrayExceptSelf {

    /*
    * T: O(n)
    * S: O(1)
    * */
    public int[] productExceptSelf(int[] nums) {
        //照答案抄的
        int[] ans = new int[nums.length];
        //从左往右撸一遍

        ans[0] = 1;
        //此时ans[i] 的物理意义是[0, i -1] 所有值的乘积
        for(int i = 1; i < nums.length; i++){
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        //r的物理意义是[i + 1, length - 1]区间段所有值得乘积
        int r = 1;
        for(int i = nums.length - 1; i >= 0 ; i--){
            ans[i] = ans[i] * r;
            r = r * nums[i];

        }
        return ans;


    }
}
