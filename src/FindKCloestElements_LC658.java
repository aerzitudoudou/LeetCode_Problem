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
}
