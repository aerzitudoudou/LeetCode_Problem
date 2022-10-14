import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

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
    //sol3.1, quick select
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



    //!!!!sol3.2, quick select, O(n), O(1),
// [3,2,1,5,6,4] and k = 2
// O(n), pivot  3, left: 2 1, right: 5,6,4
// avg O(n/2) right: 5,6,4 pivot: 5, left 4, right 6
// return 5

// [3,2,3,1,2,4,5,5,6] and k = 4
// 4
// O(n) pivot 3, left 1 2 2  right 3, 4, 5, 5, 6
// O(n/2)  pivot 3 left: [] right: 4, 5, 5, 6
//  pivot 4, left:[] right:5,5,6  left_count = k - 1
// return 4

    // n + n/2 +...n/2^k =  n( 1 + 1/2 + 1/2^k) = n*1*(1 - (1/2)^k)/(1-(1/2)) = 2n
// O(n)
    public int findKthLargest_3_2(int[] nums, int k) {

        int targetIndex = nums.length - k; //kth largest element
        int curIndex = 0;
        int l = 0, r = nums.length - 1;
        while(l <= r){
            curIndex = partition_3_2(nums, l, r);
            if(curIndex == targetIndex) return nums[curIndex];
            else if(curIndex < targetIndex){
                l = curIndex + 1;
            }else{
                r = curIndex - 1;
            }
        }

        return nums[curIndex];
    }

    private int partition_3_2(int[] nums, int l, int r){
        Random rand = new Random();
        int pivot = l + rand.nextInt(r - l + 1);
        int i = l, j = r;
        swap(nums, r, pivot);
        j--;
        while(i <= j){
            if(nums[j] >= nums[r]) j--;
            else if(nums[i] < nums[r]) i++;
            else{
                swap(nums, i , j);
                i++;
                j--;
            }
        }

        swap(nums, i, r);
        return i;
    }

    private void swap(int[] ary, int i, int j){
        int tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }
}
