import java.util.ArrayList;
import java.util.List;

public class PancakeSorting_LC969 {
    /*
        i,    j k
    k   3   2 4 1 -> 1 4 2 3 ->
    4   1   4 2 3
    2   4   1 2 3
    4   3   2 1 4
    3   1   2 3 4

    (k, length - 1] sorted
    in [0, k] : j to loop through , i to keep index the max

    reverse[0, i] : max index = 0
    reverse[0, k] : max index = k
    k--

   */
    //!!!sol1, from acwing https://www.acwing.com/video/3256/, O(n^2), O(1)
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for(int k = arr.length - 1; k >= 0; k--){
            int j = 0, i = 0;
            //find max index i
            while(j <= k){
                if(arr[j] > arr[i]){
                    i = j;

                }
                j++;
            }
            if(i == k) continue;
            //two filps to move max number index within [0,k] to the k index position
            res.add(i + 1);
            flip(arr, 0, i);
            res.add(k + 1);
            flip(arr, 0, k);

        }
        return res;
    }
    //   i j
    // 1 2 3 4

    //4  3  2   1
    private void flip(int[] arr, int i, int j){
        while(i < j){
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}
