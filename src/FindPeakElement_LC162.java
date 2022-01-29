public class FindPeakElement_LC162 {
    //sol1, from acwing, O(log(n)), O(1)
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            //if mid < mid + 1, peak in the right: if(mid + 2) < (mid + 1) then mid + 1 is peak, otherwise if going all the way up, then last element is the peak
            //if mid > mid + 1, peak must be existing in the left part
            //!!!careful condition has to include the result if do mid < mid + 1 then mid + 1 is the result, not mid, therefore condition has to be mid > mid + 1 or mid > mid - 1
            if(nums[mid] > nums[mid + 1]){
                r = mid;
            }else l = mid + 1;

        }

        return r;
    }

    //sol1.1 similar but do the left part
    public int findPeakElement1(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            //if mid < mid + 1, peak in the right: if(mid + 2) < (mid + 1) then mid + 1 is peak, otherwise if going all the way up, then last element is the peak
            //if mid > mid + 1, peak must be existing in the left part
            //!!!careful condition has to include the result if do mid < mid + 1 then mid + 1 is the result, not mid, therefore condition has to be mid > mid + 1 or mid > mid - 1
            if(nums[mid] > nums[mid - 1]){
                l = mid;
            }else r = mid - 1;

        }

        return r;
    }
}
