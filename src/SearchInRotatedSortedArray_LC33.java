public class SearchInRotatedSortedArray_LC33 {
    //sol1, from acwing, https://www.acwing.com/blog/content/31/ O(logn)
    public int search(int[] nums, int target) {
        //x >= nums[0] to find the last element in the first range
        int l = 0, r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] >= nums[0]){
                l = mid;
            }else{
                r = mid - 1;
            }
        }

        if(target >= nums[0]) l = 0;
        else {
            l++;
            r = nums.length - 1;
        }

        //x >= target x's smallest number
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] >= target) r = mid;
            else l = mid + 1;
        }

        return nums[r] == target ? r : -1;

    }
}
