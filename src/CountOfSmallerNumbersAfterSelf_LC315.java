import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountOfSmallerNumbersAfterSelf_LC315 {
    //sol1, my, merge sort, O(nlogn), O(n)
    //similar like 493 but need to store the index of the number along the merge sort, in order to update the res using the index
    public List<Integer> countSmaller(int[] nums) {
        int[] res = new int[nums.length];
        int[][] pair = new int[nums.length][2];

        //construct the pair: <value, index>
        for(int i = 0; i < nums.length; i++){
            pair[i][0] = nums[i];
            pair[i][1] = i;
        }
        mergeSort(pair, 0, nums.length - 1, res);

        List<Integer> list = new ArrayList<Integer>();
        list = Arrays.stream(res)     // IntStream
                .boxed()        // Stream<Integer>
                .collect(Collectors.toList());
        return list;
    }


    private void mergeSort(int[][] nums, int l, int r, int[] res){
        if(l >= r) return;
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid, res);
        mergeSort(nums, mid + 1, r, res);

        //count smaller numbers for each element in the left part
        for(int i = l, j = mid + 1; i <= mid; i++){
            // 6 7 8 || 1 2 9
            while(j <= r && nums[i][0] > nums[j][0]) j++;
            res[nums[i][1]] = res[nums[i][1]] + j - mid - 1;
        }

        //sort

        int[][] helper = new int[r - l + 1][2];
        int i = l, j = mid + 1, s = 0;
        while(i <= mid && j <= r){
            if(nums[i][0] < nums[j][0]){
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
        s = 0;
        i = l;
        while(i <= r){
            nums[i++] = helper[s++];
        }


    }
}
