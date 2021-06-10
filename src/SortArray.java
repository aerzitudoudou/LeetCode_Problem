import java.util.Arrays;
import java.util.Random;

public class SortArray {
    //quick sort
    public int[] sortArray(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int l, int r){
        if(l >= r) return;
        int i = l, j = r - 1;
        Random rand = new Random();
        int pivotIndex = l + rand.nextInt(r - l + 1);
        swap(nums, pivotIndex, r);
        int pivot = nums[r];

        /*
        (j, r -1] number >=j
        [0, i) number <= i
        [i, j] tbd

        */
        while(i <= j){
            if(nums[j] >= pivot){
                j--;
            }else if(nums[i] <= pivot){
                i++;
            }else{
                swap(nums, i++, j--);
            }
        }
        swap(nums, r, i);
        sort(nums, l, j);
        sort(nums, i + 1, r);
    }

    private void swap(int[] nums, int l, int r){
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

}