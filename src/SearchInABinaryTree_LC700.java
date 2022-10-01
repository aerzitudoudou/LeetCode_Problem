public class SearchInABinaryTree_LC700 {
    //sol1, my, T: O(h)<average: O(logn), worst O(n)>, S: O(1)
     public TreeNode searchBST_1(TreeNode root, int val) {
         if(root == null) return root;
         while(root != null){
             if(val == root.key) return root;
             else if(val > root.key){
                 root = root.right;
             }else{
                 root = root.left;
             }
         }

         return null;

     }


    //!!!!sol2, cleaner code, same T and S as above
    public TreeNode searchBST_2(TreeNode root, int val) {
        while(root != null && val != root.key){
            root = val > root.key ? root.right : root.left;
        }

        return root;

    }
}
