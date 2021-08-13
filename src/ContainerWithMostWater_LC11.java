public class ContainerWithMostWater_LC11 {
    //sol1, from acwing, greedy, O(n)
    public int maxArea(int[] height) {
        int res = 0, i = 0, j = height.length - 1;
        while(i < j){
            res = Math.max(Math.min(height[i], height[j]) * (j - i), res);
            if(height[i] < height[j]) i++;
            else j--;
        }
        return res;
    }
}
