public class SearchInRotatedSortedArrayII_LC81 {
    //sol1, from acwing,https://www.acwing.com/video/1424/
    // remove the duplicate nums if there are any from 2nd partm so that first half x >= nums[0]
    //average O(logn) worst case O(n)
    public boolean search(int[] nums, int target) {
        int R = nums.length - 1;
        while(R >= 0 && nums[R] == nums[0]){
            R--;
        }
        if(R < 0) return nums[0] == target;

        int l = 0, r = R;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] >= nums[0]) l = mid;
            else r = mid - 1;
        }

        if(target >= nums[0]) l = 0;
        else{
            l = r + 1;
            r = R;
        }

        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] >= target) r = mid;
            else l = mid + 1;
        }

        return nums[r] == target;
    }
}
