public class ReversePairs_LC493 {
      /*
         count the number of reverse pairs during merge sort
         O(nlogn), O(n)
         -on every level: for each node on the recursion tree, find all the reverse pairs between two parts: O(r - l), merge: O(r - l), adding up all the nodes, the time complexity becomes O(n)
         -logn levels on the recursion tree
          1 2 3 4 || 1 3


    */

    public int reversePairs(int[] nums) {

        return mergeSort(nums, 0, nums.length - 1);


    }

    private int mergeSort(int[] nums, int l, int r){

        int res = 0;
        if(l >= r) return 0;
        int mid = l + (r - l) / 2;
        res += mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);



        //current level reverse pair calculation
        //5 7 9 || 1 2 3
        //T: O(r - l)
        for(int i = l, j = mid + 1; i <= mid; i++){
            while(j <= r && (long)nums[i] > 2 * (long)nums[j]) {
                j++;
            }
            res += j - mid - 1;
            //!!!no need to set j back to mid + 1, TLE if nums[i] > 2 * nums[j] then nums[i + k] must > 2 * nums[j] since both left and right part are sorted: nums[i + k] > nums[i]
            // j = mid + 1;
        }


        //current level merge
        //T: O(r - l)
        //!!!don't open an array of size nums, it will cause worst time case O(n) in this step and O(n^2logn) for the merge sort -> bottom level recursion tree, each node needs a O(n) operation to initiate a new array
        int[] helper = new int[r - l + 1];

        int i = l, j = mid + 1;
        int s = 0;
        while(i <= mid && j <= r){
            if(nums[i] <= nums[j]){
                helper[s++] = nums[i++];
            }else{
                helper[s++] = nums[j++];
            }
        }

        while(i <= mid){
            helper[s++] = nums[i++];
        }
        while(j <= r){
            helper[s++] = nums[j++];
        }

        i = l;
        j = 0;
        while(i <= r){
            nums[i++] = helper[j++];
        }

        return res;


    }

}
