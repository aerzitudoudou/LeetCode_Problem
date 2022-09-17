public class MaximumAverageSubtree {
    //sol1, my, O(n), O(h)
    double max = Double.MIN_VALUE;
    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return max;
    }

    private int[] dfs(TreeNode root){
        //int[]:[sum of value of all childs + current node, count of all childs + current node(1)], update max on each node
        if(root == null) return new int[]{0,0};
        int[] l = dfs(root.left), r = dfs(root.right);
        int sum = root.key + l[0] + r[0];
        int value = 1 + l[1] + r[1];
        max = Math.max((double)sum / value, max);
        return new int[]{sum, value};

    }
}
