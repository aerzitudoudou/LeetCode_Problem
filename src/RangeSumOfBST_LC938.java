public class RangeSumOfBST_LC938 {
    //!!!sol2: dfs
    //T: amortized: O(logn + number of points between high and low). worst case O(n)
    //S: O(h) h is the height of the tree
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) return 0;
        if(low > root.key) return rangeSumBST(root.right, low, high);
        if(high < root.key) return rangeSumBST(root.left, low, high);

        return root.key + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);

    }




//     //sol1: my, O(n) O(h)
//     public int rangeSumBST(TreeNode root, int low, int high) {
//         int[] res = new int[1];
//         helper(root, low, high, res);
//         return res[0];

//     }

//     private void helper(TreeNode root, int low, int high, int[] res){
//         if(root == null) return;
//         helper(root.left, low, high, res);
//         if(root.val >= low && root.val <= high){
//             res[0] += root.val;
//         }
//         helper(root.right, low, high, res);

//     }




}
