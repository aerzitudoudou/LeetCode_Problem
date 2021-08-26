import java.util.Arrays;

public class NextPermutation_LC31 {
    public void nextPermutation(int[] nums) {
        //find drop number
        int i = nums.length - 1;
        while(i >= 1 && nums[i - 1] >= nums[i]){
            i--;
        }
        if(i == 0){
            Arrays.sort(nums);
            return;
        }
        i--;
        int j = nums.length - 1;
        while(j > i && nums[j] <= nums[i]){
            j--;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;

        Arrays.sort(nums, i + 1, nums.length);
        return;

    }


    //from lowest digit to the highest digit,
    //first digit that drops(3): *****    3 8642
    //switch with the first number that's bigger than it in 8642, which is 4
    //**** 4 8632 -> sort 8632 to be 2368
}
