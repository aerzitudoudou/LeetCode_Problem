public class MinimumSubtree_Lint596 {
    //sol1, my, O(n), O(h)
    int min = Integer.MAX_VALUE;
    TreeNode res = null;
    public TreeNode mininumSubtree(TreeNode root){
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root){
        if(root == null) return 0;
        int sum = dfs(root.left) + dfs(root.left) + root.key;
        if(sum < min){
            min = sum;
            res = root;
        }
        return sum;
    }
}
