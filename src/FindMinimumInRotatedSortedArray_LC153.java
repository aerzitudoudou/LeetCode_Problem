public class FindMinimumInRotatedSortedArray_LC153 {
    //sol1, find biggest number bigger than nums[0], the next element to the right is the result, O(logn), O(1)
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] >= nums[0]){
                l = mid;
            }else r = mid - 1;


        }
        return r == nums.length - 1 ? nums[0] : nums[r + 1];

    }

    //sol2, similarly, find smallest number less than nums[0], O(logn), O(1)
    public int findMin2(int[] nums) {
        int target = nums[0];
        int l = 0, r = nums.length - 1;
        // if(nums[r] > nums[l]) return target;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] < target){
                r = mid;
            }else l = mid + 1;
        }

        //if monotonic ascending, e.g. 1,2,3,4,5, careful !! r = l ends at index = length - 1 which is largest
        return nums[r] < target ? nums[r] : target;

    }
}
