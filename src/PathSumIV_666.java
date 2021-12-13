import java.util.HashMap;
import java.util.Map;

public class PathSumIV_666 {
    //sol1, from https://leetcode.com/problems/path-sum-iv/discuss/106892/Java-solution-Represent-tree-using-HashMap
    //O(n), O(n)
    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public int pathSum(int[] nums) {
        //map: <Key(xy), value>
        //left node: x + 1, 2* y - 1
        //right node: x + 1, 2 * y

        //construct the map
        for(int num : nums){
            map.put(num/10, num%10);
        }

        dfs(nums[0] / 10, 0);
        return res;
    }


    private void dfs(int root, int preSum){
        if(!map.containsKey(root)){
            return;
        }

        int x = root/10;
        int y = root%10;
        int left = (x + 1) * 10 + (2 * y - 1);
        int right  = (x + 1) * 10 + 2 * y;
        //leaf node, add sum to the total
        if(!map.containsKey(left) && !map.containsKey(right)){
            res += preSum + map.get(root);
            return;
        }
        dfs(left, preSum + map.get(root));
        dfs(right, preSum + map.get(root));
    }
}
