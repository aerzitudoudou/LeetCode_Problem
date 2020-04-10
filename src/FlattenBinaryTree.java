public class FlattenBinaryTree {
    public TreeNode flatten(TreeNode root) {
//        TreeNode dummy = new TreeNode(0);
//        flatten(root, dummy);
//        return dummy.right;
        TreeNode[] prev = new TreeNode[1];
        flatten(root, prev);
        return root;

        // Write your solution here
    }
//
//    public void flatten(TreeNode root, TreeNode dummy){
//        if(root == null){
//            return;
//        }
//        TreeNode tmp = dummy;
//        while(tmp.right != null){
//            tmp = tmp.right;
//        }
//
//        tmp.right = new TreeNode(root.key);
//        tmp.left = null;
//        flatten(root.left, dummy);
//        flatten(root.right, dummy);
//    }

     public void flatten(TreeNode root, TreeNode[] prev){
        if(root == null){
            return;
        }
        flatten(root.right, prev);
        flatten(root.left, prev);
        root.right = prev[0];
        root.left = null;
        prev[0] = root;
     }

}
