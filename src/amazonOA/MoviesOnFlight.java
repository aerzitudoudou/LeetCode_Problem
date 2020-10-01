package amazonOA;
//https://www.lintcode.com/problem/movies-on-flight/description

import java.util.Arrays;
import java.util.Comparator;

class Pair{
    int index;
    int val;
    public Pair(int index, int val){
        this.index = index;
        this.val = val;
    }
}
public class MoviesOnFlight {
    /**
     * @param arr: An integer array
     * @param d: An integer
     * @return: return the pair of movies index with the longest total duration
     */
    //T: O(nlogn) where n the length of the array
    //S: O(n)
    public int[] FlightDetails(int[] arr, int d) {
        //corner cases
        if(arr == null || arr.length == 0 || d - 30 < 0) return new int[0];

        Pair[] pairs = new Pair[arr.length];
        for(int i = 0; i < arr.length; i++){
            pairs[i] = new Pair(i, arr[i]);
        }

        Arrays.sort(pairs, new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                if(p1.val == p2.val) return 0;
                return p1.val < p2.val ? -1 : 1;
            }
        });

        int left = 0, right = arr.length - 1;
        int i = 0, j = 0;
        int maxRes = 0;
        while(left < right){
            if(pairs[left].val + pairs[right].val <= d - 30){
                if(maxRes < pairs[left].val + pairs[right].val){
                    maxRes =  pairs[left].val + pairs[right].val;
                    i = pairs[left].index;
                    j = pairs[right].index;
                }

                left++;
            }else{
                right--;
            }

        }


        return i < j ? new int[]{i, j} : new int[]{j, i};

    }
}