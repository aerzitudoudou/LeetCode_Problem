public class SearchInRotatedSortedArray_LC33 {
    //!!!sol1, from acwing, find peak, https://www.acwing.com/blog/content/31/ O(logn)
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

    //sol2, my, find minimal, O(logn), O(1)
    public int search1(int[] nums, int target) {
        int first = nums[0];
        int len = nums.length;
        //find boundary point: minimum element in the array
        int l = 0, r = len - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] < first){
                r = mid;
            }else l = mid + 1;
        }

        r = nums[r] < first ? r : 0;

        //r is the minimum number index
        if(target <= nums[len - 1]){
            l = r;
            r = len - 1;
        }else{
            l = 0;
            r = r - 1;
        }

        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] <= target){
                l = mid;
            }else r = mid - 1;
        }

        return (nums[l] == target) ? l : -1;
    }
}
