import java.util.Arrays;

public class ValidTriangleNumber_LC611 {
    //max: i
    //mid: j
    //min: k
    //sol1, from https://www.acwing.com/video/2094/
    //1. sort
    //2. iterate max value i
    //3. iterate mid value j
    //4. two pointer to find the numbers of triangles with i and j
    //O(n^2), O(1)
    public int triangleNumber(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for(int i = nums.length - 1; i >= 0; i--){
            int k = 0;
            for(int j = i - 1; k < j && j >= 0; j--){
                while(k < j && nums[k] <= nums[i] - nums[j]) k++; //out of loop: k + j > i, j move to the left, i - j becomes bigger, then Knew >= Kold to satisfy k + j > i
                res += j - k;
            }
        }

        return res;

    }
}
