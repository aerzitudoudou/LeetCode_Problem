import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestNumbersInUnsortedArray_Lint461 {
    //sol1, my, O(nlogk), O(k)
     public int kthSmallest(int k, int[] nums) {
         PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());
         for(int i = 0; i < nums.length; i++){
             maxHeap.offer(nums[i]);
             while(maxHeap.size() > k){
                 maxHeap.poll();
             }

         }
         return maxHeap.poll();
     }

    //!!!sol2, my, quick select, similar to lc 215, O(n), worst O(n^2), S: O(1)
    public int kthSmallest_1(int k, int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = partition(nums, l, r);
            if(mid == k - 1) break;
            else if(mid > k - 1) r = mid - 1;
            else l = mid + 1;
        }
        return nums[k - 1];
    }

    //[0, l) <= pivot, [l, r] tbd, (r, length - 1] >= pivot
    private int partition(int[] nums, int l, int r){
        int pivot = nums[l];
        while(l < r){
            while(l < r && nums[r] >= pivot) r--;
            nums[l] = nums[r];
            while(l < r && nums[l] <= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }
}
