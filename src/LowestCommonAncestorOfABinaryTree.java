public class LowestCommonAncestorOfABinaryTree {
    //if one node is the parent of the other return that node --> no need to search the other
    //if one node is found but not the parent of the other also return that node
    //if cur.left = p and cur.right = q return the current node

    //base case: if root == null return null
    //what if p or q doesn't exist?
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root.key == p.key || root.key == q.key){
            return root;
        }



        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);
        //no need to check value
        // if(left.val == p.val && right.val == q.val || left.val == q.val && right.val == p.val){
        //     return root;
        // }else if(left.val == p.val || left.val == q.val){
        //     return left;
        // }else if(right.val == q.val || right.val == p.val){
        //     return right;
        // }else{
        //     return null;
        // }

        if(left == null && right == null){
            return null;
        }else if(left == null || right == null){
            return left == null ? right : left;
        }else{
            return root;
        }

         /*
        better way to handle above:


        if(left != null && right != null){
            return root;
        }
        return left == null ? right : left;
        */





    }
}
