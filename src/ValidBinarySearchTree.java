import java.util.Deque;
import java.util.LinkedList;
//lc 98
public class ValidBinarySearchTree {



    //!!!!!!!!sol 1: bfs in order traversal is a sorted increasing array
    //T: N S: H
    //pre represents the previous in order traverse treenode
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null, cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.offerFirst(cur);
                cur = cur.left;
            }
            TreeNode tmp = stack.pollFirst();
            if(pre != null && pre.key >=  tmp.key) return false;
            pre = tmp;
            cur = tmp.right;

        }
        return true;
    }

    //!!!!sol 2: concise recursive way
     public boolean isValidBST1(TreeNode root) {
         return isValidBST(root, null, null);
     }

     private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max){
         if(root == null) return true;
         return (min == null || root.key > min.key) && (max == null || root.key < max.key) && isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
     }

    //sol1, my, recursive, O(n), O(h)
     boolean res = true;
     public boolean isValidBST(TreeNode root) {
         if(root == null) return true;
         //in order traverse monotonic increasing
         dfs(root);
         return res;

     }


     private long[] dfs(TreeNode root){
         if(root == null || !res) return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
         long[] l = dfs(root.left), r = dfs(root.right);
         if(root.key <= l[1] || root.key >= r[0])
             res = false;
         long max = Math.max(Math.max(l[1], r[1]), root.key);
         long min = Math.min(Math.min(l[0], r[0]), root.key);
         return new long[]{min, max};
     }





}
