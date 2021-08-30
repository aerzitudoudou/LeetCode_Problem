public class FindFirstAndLastPositionOfElementInSortedArray_LC34 {
    //sol1, my, O(logn + k), O(1)
     public int[] searchRange1(int[] nums, int target) {
         if(nums == null || nums.length == 0){
             return new int[]{-1, -1};
         }
         //find 1st occurance of the num or the first number > target, extend to the right
         int l = 0, r = nums.length - 1;
         while(l < r){
             int mid = l + (r - l) / 2;
             if(nums[mid] >= target){
                 r = mid;
             }else{
                 l = mid + 1;
             }
         }

         //r
         if(nums[r] != target) return new int[]{-1, -1};

         int start = r;
         while(r < nums.length && nums[r] == target){
             r++;
         }

         return new int[]{start, r - 1};


     }

    //!!!sol2, from acwing https://www.acwing.com/video/1353/, Olog(n), O(1)
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }
        //find left boundary
        int l = 0, r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] >= target){
                r = mid;
            }else{
                l = mid + 1;
            }
        }

        if(nums[r] != target){
            return new int[]{-1, -1};
        }

        int L = r;
        //find right boundary
        l = 0;
        r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] <= target){
                l = mid;
            }else{
                r = mid - 1;
            }
        }

        return new int[]{L, r};
    }

}
