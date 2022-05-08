public class SortColors_LC75 {

    /*
    !!sol1, my, O(n), O(1)
   0: [0,i)
   1: [i,j)
   tbd:[j, k]
   2: (k, length - 1]
                    i,    k   j
   0      0    0    1     1    2

   j = 0 swap i,j i++ j++
   j = 1 j++
   j = 2 swap j, k k--

   */
    public void sortColors(int[] nums) {
        int i = 0, j = 0, k = nums.length - 1;
        while(j <= k){
            if(nums[j] == 0){
                swap(nums, i, j);
                i++;
                j++;
            }else if(nums[j] == 1){
                j++;
            }else{
                swap(nums, j, k);
                k--;
            }
        }

    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
