public class MoveZeros_LC283 {
    //[0,i) sorted items
    //j: current item being examined
    //        i  j
    // 1 3 12 3 12
    //
    //
    public void moveZeroes(int[] nums) {
        int i = 0;
        for(int j = 0; j < nums.length; j++){
            if(nums[j] != 0){
                nums[i] = nums[j];
                i++;
            }
        }
        while(i < nums.length){
            nums[i++] = 0;
        }

    }
}
