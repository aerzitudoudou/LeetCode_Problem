public class SignOfTheProductOfAnArray_LC1822 {
    //sol1, my, O(n), O(1)
     public int arraySign1(int[] nums) {
         int neg = 0;
         for(int i = 0; i < nums.length; i++){
             if(nums[i] == 0) return 0;
             if(nums[i] < 0) neg++;
         }
         return neg % 2 == 0 ? 1 : -1;

     }

    //sol 1.1, no need to % 2
    public int arraySign(int[] nums) {
        int ans = 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) return 0;
            if(nums[i] < 0) ans*= -1;
        }
        return ans;

    }
}
