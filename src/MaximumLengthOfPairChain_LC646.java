import java.util.Arrays;

public class MaximumLengthOfPairChain_LC646 {
    //sol1, my, greedy, O(nlogn), O(1)
    //sort array by the pair's endpoint, take array if arrayCur[0] > arrayPrev[1]
    public int findLongestChain(int[][] pairs) {
        int res = 1;
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int r = pairs[0][1];
        for(int i = 1; i < pairs.length; i++){
            if(pairs[i][0] > r){
                res++;
                r = pairs[i][1];
            }

        }

        return res;
    }

}
