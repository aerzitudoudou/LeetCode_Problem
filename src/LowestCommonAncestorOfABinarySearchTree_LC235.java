public class LowestCommonAncestorOfABinarySearchTree_LC235 {
    //sol1, my, O(n), O(h)
//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//          if(root == null || root.key == p.key || root.key == q.key || (p.key < root.key && root.key < q.key) || (q.key < root.key && root.key < p.key)) return root;

//          if(p.key < root.key && q.key < root.key) return lowestCommonAncestor(root.left, p, q);
//          return lowestCommonAncestor(root.right, p, q);
//     }

    //!!! sol2, from solution: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/ ,cleaner code, O(n), O(h)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(p.key < root.key && q.key < root.key) {
            return lowestCommonAncestor(root.left, p, q);
        }else if(p.key > root.key && q.key > root.key){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }
}
