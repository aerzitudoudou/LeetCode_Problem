import java.util.HashMap;
import java.util.Map;

public class DegreeOfAnArray_LC697 {

    //sol1, my, O(n + m), O(m)
    //n: nums in the ary m: different num in the array
    public int findShortestSubArray(int[] nums) {
        int degree = 0;
        //num, ary
        Map<Integer, int[]> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            //ary: number of times, first index, last index
            int[] ary = map.getOrDefault(nums[i], new int[]{0, i, -1});
            ary[0]++;
            ary[2] = i;
            map.put(nums[i], ary);
            if(ary[0] > degree){
                // System.out.println(nums[i] + ":" + ary[0] + "times");
                degree = ary[0];
            }
        }

        int res = Integer.MAX_VALUE;
        for(Map.Entry<Integer, int[]> entry : map.entrySet()){
            int ary[] = entry.getValue();
            if(ary[0] == degree){
                res = Math.min(res, ary[2] - ary[1] + 1);
            }
        }

        return res;
    }



}
