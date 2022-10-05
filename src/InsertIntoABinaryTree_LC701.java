import java.util.Deque;
import java.util.LinkedList;

public class InsertIntoABinaryTree_LC701 {

    //sol1.1, my , binary search on the binary tree, O(h),O(1)
     public TreeNode insertIntoBST_1_1(TreeNode root, int val) {
         TreeNode node = new TreeNode(val);
         TreeNode originalRoot = root;
         if(root == null) return node;
         TreeNode pre = null;
         while(root != null){
             if(val >= root.key){
                 pre = root;
                 root = root.right;

             }else{
                 pre = root;
                 root = root.left;
             }
         }

         if(val > pre.key) pre.right = node;
         else pre.left = node;

             return originalRoot;
     }
    //!!!sol1.2, iterative, O(h), O(1), bst的性质，一定能找到一个叶子节点来安插非重复node.val值大了往左子树走，val值小了往右子树走，走到不能走就是安插点
    public TreeNode insertIntoBST_1_2(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        TreeNode originalRoot = root;
        if(root == null) return node;
        while(root != null){
            if(val > root.key){
                if(root.right == null) {
                    root.right = node;
                    break;
                }
                root = root.right;
            }else{
                if(root.left == null) {
                    root.left = node;
                    break;
                }
                root = root.left;
            }
        }

        return originalRoot;

    }

     //!!!sol2, recursive,O(h), O(h)
     public TreeNode insertIntoBST_2(TreeNode root, int val) {
         if(root == null) return new TreeNode(val);
         if(val < root.key) root.left = insertIntoBST_2(root.left, val);
         else root.right = insertIntoBST_2(root.right, val);
         return root;

     }

}
