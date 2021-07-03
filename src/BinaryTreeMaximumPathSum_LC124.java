public class BinaryTreeMaximumPathSum_LC124 {
    //recursion三部曲，sol1, my, O(n), O(h)
    public int maxPathSum(TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        helper(root, res);
        return res[0];
    }

    private int helper(TreeNode root, int[] res){
        if(root == null) return 0;
        int left = Math.max(helper(root.left, res),0);
        int right = Math.max(helper(root.right, res), 0);
        res[0] = Math.max(res[0], left + root.key + right);
        return Math.max(left, right) + root.key;
    }
}
