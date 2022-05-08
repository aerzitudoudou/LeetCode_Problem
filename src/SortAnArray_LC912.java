import java.util.Random;

public class SortAnArray_LC912 {
    //quick sort
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    //       j i
    // 2 4 5 1 6
    private void quickSort(int[] nums, int l, int r){
        if(l >= r) return;
        //find pivot index[l, r]
        Random rand = new Random();
        int pivotIndex = l + rand.nextInt(r - l + 1); //[0, r - l] + l = [l, r]

        //move pivot to the right
        swap(nums, pivotIndex, r);
        //[0, i) <= pivot    (j, r - 1] >= pivot [i, j] tbd
        int i = l, j = r - 1;
        while(i <= j){
            if(nums[j] >= nums[r]){
                j--;
            }else if(nums[i] <= nums[r]){
                i++;
            }else{
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        swap(nums, i, r);
        quickSort(nums, i + 1, r);
        quickSort(nums, l, j);
    }

    private void swap(int[] nums, int l, int r){
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

}