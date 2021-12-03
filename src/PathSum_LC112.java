public class PathSum_LC112 {
    //sol1, my, dfs, O(n), O(h)
     boolean hasSum = false;
     public boolean hasPathSum(TreeNode root, int target) {
         dfs(root, target, 0);
         return hasSum;

     }

     private void dfs(TreeNode root, int target, int preSum){
         //base case
         if(root == null) return;
         //quit in advance: is leaf node and sum equals
         if(root.key + preSum == target && root.left == null && root.right == null){
             hasSum = true;
             return;
         }

         if(root.left != null){
             dfs(root.left, target, root.key + preSum);
         }

         if(root.right != null){
             dfs(root.right, target, root.key + preSum);
         }


     }


    //sol2, from https://leetcode.com/problems/path-sum/discuss/36378/AcceptedMy-recursive-solution-in-Java
    // recursion with return type, O(n), O(h)
    public boolean hasPathSum2(TreeNode root, int target) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.key == target) return true;

        return hasPathSum(root.left, target - root.key) || hasPathSum(root.right, target - root.key);


    }

}
