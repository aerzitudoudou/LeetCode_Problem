import java.util.Random;

public class RandomPickIndex_LC398 {
    int[] nums;

    public RandomPickIndex_LC398(int[] nums) {
        this.nums = nums;
    }
    //reservoir sampling
    public int pick(int target) {
        int k = 0, x = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != target) continue;
            k++;
            Random rand = new Random();
            if(rand.nextInt(k) == 0){
                   x = i;
            }
        }
        return x;

    }
}
