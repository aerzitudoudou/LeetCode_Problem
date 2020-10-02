package amazonOA;
//https://www.lintcode.com/problem/minimum-distance-between-bst-nodes/description
//leetcode 783


class TreeNode{
    TreeNode left;
    TreeNode right;
    Integer val;
    public TreeNode(Integer val){
        this.val = val;
    }
}

//Because it is a binary search tree, it's inorder traverse will be sorted from smallest to largest values.
//keep track of the diff between two adjacent in order result and find the minimum
//T: n n is the number of nodes on the tree S: h h is the height of the tree
public class MinDiffInBST {
    Integer prev, ans;
    public int minDiffInBST(TreeNode root) {
        prev = null;
        ans = Integer.MAX_VALUE;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (prev != null)
            ans = Math.min(ans, node.val - prev);
        prev = node.val;
        dfs(node.right);
    }



}
