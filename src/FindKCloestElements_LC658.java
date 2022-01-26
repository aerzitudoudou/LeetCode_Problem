import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindKCloestElements_LC658 {
    //!!!sol1, from acwing, https://www.acwing.com/video/2428/, O(logn + k), O(1)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();

        if(arr == null) return list;
        int len = arr.length;
        if(len == 0 || len < k) return list;

        //first binary serach - part1: < x part2: >=x
        int l = 0, r = len - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(arr[mid] >= x){
                r = mid;
            }else l = mid + 1;
        }

        //r is the smallest number in part2
        //1 2 3 4 6 9     x = 7   smaller number >= 7 is 9, but closest number to 7 is 6. r represents the closest number index to x
        if(r > 0){
            r = Math.abs(arr[r - 1] - x) <= Math.abs(arr[r] - x) ? r - 1 : r;
        }
        int left = r, right = r, count = k;
        //range of valid numbers: (left, right)
        count--;
        left--;
        right++;


        for(int i = 0; i < count; i++){
            if(left < 0) right++;
            else if(right >= len) left--;
            else{
                if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)){
                    left--;
                }else{
                    right++;
                }
            }

        }


        for(int i = left + 1; i < left + 1 + k; i++){
            list.add(arr[i]);
        }
        return list;

    }

    //!!!sol2, from https://leetcode.com/problems/find-k-closest-elements/discuss/106426/JavaC%2B%2BPython-Binary-Search-O(log(N-K)-%2B-K)
    //O(log(n - k) + k), O(1)
    public List<Integer> findClosestElements1(int[] arr, int k, int x){
        //x > (arr[mid] + arr[mid + k])/2 cut left part => 2x > arr[mid] + arr[mid + k]   -> x - arr[mid + k] > arr[mid] - x
        //x <= (arr[mid] + arr[mid + k])/2 cut right part (since for equal abs value, favor left index than right) => x - arr[mid + k] <= arr[mid] - x
        //sliding window with starting pointer moving between [0, arr.length - k]    0 1 2 3 5    k = 4 , x = 3

        //Note that, you SHOULD NOT compare the absolute value of abs(x - A[mid]) and abs(A[mid + k] - x).
        //It fails at cases like A = [1,1,2,2,2,2,2,3,3], x = 3, k = 3
        
        List<Integer> list = new ArrayList<>();
        int l = 0, r = arr.length - k;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(x - arr[mid + k] <= arr[mid] - x){
                r = mid;
            }else l = mid + 1;
        }

        for(int i = r ; i < r + k; i++){
            list.add(arr[i]);
        }

        return list;


    }




}
