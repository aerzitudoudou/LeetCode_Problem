import java.util.Arrays;

public class NextPermutation_LC31 {
    public void nextPermutation(int[] nums) {
        //find drop number
        int i = nums.length - 1;
        while(i >= 1 && nums[i - 1] >= nums[i]){
            i--;
        }
        //corner case: last in the lexicographical order
        if(i == 0){
            Arrays.sort(nums);
            return;
        }
        i--;
        //find first number bigger than drop
        int j = nums.length - 1;
        while(j > i && nums[j] <= nums[i]){
            j--;
        }

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        //sort the remaining after to make the remaining least in lexicographical
        Arrays.sort(nums, i + 1, nums.length);
        return;

    }


    //from lowest digit to the highest digit,
    //first digit that drops(3): *****    3 8642
    //switch with the first number that's bigger than it in 8642, which is 4
    //**** 4 8632 -> sort 8632 to be 2368
}
