import java.util.Arrays;
import java.util.Random;
//https://xujimmy.com/2017/10/15/shuffle.html
public class ShuffleAnArray_LC384 {
    int[] nums;
    public ShuffleAnArray_LC384(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] cur = Arrays.copyOfRange(nums, 0, nums.length);
        Random rand = new Random();

        for(int i = 0 ; i < cur.length; i++){
            int index = rand.nextInt(i + 1);//random(1) : [0,1) will be only returning 0 | random(2) [0,2)
            swap(cur, index, i);
        }
        return cur;
    }

    private void swap(int[] cur, int i, int j){
        int tmp = cur[i];
        cur[i] = cur[j];
        cur[j] = tmp;
    }
}
