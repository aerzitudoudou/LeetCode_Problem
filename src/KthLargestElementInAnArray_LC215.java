import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray_LC215 {

     //sol1, my, maxHeap, O(nlogn + k), O(n)
     public int findKthLargest_1(int[] nums, int k) {
         PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
         for(int i = 0; i < nums.length; i++){
             maxHeap.offer(nums[i]);
         }
         for(int i = 1; i < k; i++){
             maxHeap.poll();
         }

         return maxHeap.poll();
     }

    //sol2, my, min heap, size = k, O(nlogk), O(k)
    public int findKthLargest_2(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i++){
            minHeap.offer(nums[i]);
            if(minHeap.size() > k){
                minHeap.poll();
            }

        }

        return minHeap.poll();
    }
    //sol3, quick select
    /*
        k
    6 5 4 3 2 1   k = 2 return 1
   */
    //average O(n) worst O(n^2)
    //space: O(1)
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
