public class CloestBinarySearchTreeValue_LC270 {
    //binary search on a binary tree
    //T: O(logn) if balanced tree or O(h),worst case O(n) 
    //S: O(1)
    public int closestkeyue(TreeNode root, double target) {
        int res = root.key;
        TreeNode cur = root;
        while(cur != null){
            res = Math.abs(cur.key - target) < Math.abs(res - target) ? cur.key : res;
            cur = cur.key < target ? cur.right : cur.left;
        }
        return res;
    }
}
