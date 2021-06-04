public class KthLargestElementInAnArray {
    /*
        k
    6 5 4 3 2 1   k = 2 return 1
   */
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = partition(nums, l, r);
            if(mid == k - 1) break;
            else if(mid < k - 1){
                l = mid + 1;
            }else{
                r = mid - 1;
            }


        }
        return nums[k - 1];


    }
    //nums[l] = pivot
    //[0,i)>= pivot [i,j]:TBD (j,r] <= pivot
    /*
    pivot = 1
    pivot
                  k
    0      1      2
                 lr
    1      2      3
    3      2      1

    */
    private int partition(int[] nums, int l, int r){
        int pivot = nums[l];
        while(l < r){
            while(l < r && nums[r] <= pivot) r--;
            nums[l] = nums[r];
            while(l < r && nums[l] >= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }
}
