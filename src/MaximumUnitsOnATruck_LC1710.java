import java.util.Arrays;

public class MaximumUnitsOnATruck_LC1710 {
    //Sol1, my, O(n * m + nlogn), O(1)
    public int maximumUnits1(int[][] ary, int truckSize) {
        int res = 0;
        Arrays.sort(ary, (a, b) -> b[1] - a[1]);
        int count = 0;
        for(int i = 0; i < ary.length; i++){
            int[] curAry = ary[i];
            int num = curAry[0];
            while(num > 0 && count < truckSize){
                res += curAry[1];
                count ++;
                num--;
            }
        }
        return res;
    }

    //!!!!sol2, instead of adding one by one just multiply with valid boxes num
    //O(nlogn + n), O(1)
    public int maximumUnits(int[][] ary, int truckSize) {
        int res = 0;
        Arrays.sort(ary, (a, b) ->
             b[1] - a[1]
        );

        int i = 0;
        while(i < ary.length && truckSize > 0){
            int num = ary[i][0];
            int validNum = Math.min(truckSize, num);
            res += validNum * ary[i][1];
            truckSize -= validNum;
            i++;
        }
        return res;
    }
}
