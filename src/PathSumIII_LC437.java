import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathSumIII_LC437 {

    //sol1, my, dfs, O(n^2), O(h)
     int result = 0;
     public int pathSum(TreeNode root, int target) {
         //list to save presum from root
         List<Integer> list = new ArrayList<>();
         //default presum is 0
         list.add(0);
         dfs(list, root, target);
         return result;

     }


     private void dfs(List<Integer> list, TreeNode root, int target){
         if(root == null) return;

         int curSum = root.key + list.get(list.size() - 1);

         list.add(curSum);
         for(int i = 0; i < list.size() - 1; i++){
             if(curSum - list.get(i) == target) result++;
         }

         if(root.left != null){
             dfs(list, root.left, target);

         }

         if(root.right != null){
             dfs(list, root.right, target);
         }

         list.remove(list.size() - 1);

     }


    //!!!sol2, acwing, optimize to use hashmap to find the sum in O(1) instead of looping through the arraylist
    //dfs, O(n), O(n)

    public int pathSum2(TreeNode root, int target) {
        //list to save presum from root<presum, counts>
        Map<Integer, Integer> map = new HashMap<>();
        //default presum is 0
        map.put(0, 1);
        dfs(map, root, target, 0);
        return result;

    }


    private void dfs(Map<Integer, Integer> map, TreeNode root, int target, int preSum){
        if(root == null) return;

        int curSum = root.key + preSum;


        //find the number of curSum - previous nodes' preSums = target on this current node
        result += map.getOrDefault(curSum - target, 0);

        //update map with current node's preSum
        int curCount = map.getOrDefault(curSum, 0);
        curCount++;
        map.put(curSum, curCount);

        dfs(map, root.left, target, curSum);
        dfs(map, root.right, target, curSum);


        //tu chu lai, if remove sum which has 0 count then the space complexity becomes O(h)
        // if(curCount == 1){
        //     map.remove(curSum);
        // }else{
        //     map.put(curSum, --curCount);
        // }

        map.put(curSum, --curCount);



    }
}
