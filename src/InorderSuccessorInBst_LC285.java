import java.util.Deque;
import java.util.LinkedList;

public class InorderSuccessorInBst_LC285 {
    //sol1, my, in order traverse iteratively, set a flag to save the p node find status, use the find status to return the next node find as the in order parent
    //O(n), O(h)
     public TreeNode inorderSuccessor_1(TreeNode root, TreeNode p) {
         if(root == null) return null;
         Deque<TreeNode> stack = new LinkedList<>();
         TreeNode cur = root;
         boolean isSeen = false;
         while(cur != null || !stack.isEmpty()){
             while(cur != null){
                 stack.offerFirst(cur);
                 cur = cur.left;
             }
             TreeNode tmp = stack.pollFirst();
             if(isSeen) return tmp;
             //find the next greater element
             if(tmp == p) isSeen = true;
             cur = tmp.right;
         }

         return null;

     }

    //!!!!sol2, from https://leetcode.com/problems/inorder-successor-in-bst/solution/,
    // conduct a binary search on the bst tree itself, reduce the search domain(possible nodes where successor may be located in) by half each time
    //t: average: O(logn) for balanced BST, worse case: O(n) for the skewed tree
    //s: O(1), no extra space/data structure needed
    public TreeNode inorderSuccessor_2(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while(root != null){
            if(p.key >= root.key){ //successor can't be within the left subtree including current root
                root = root.right;
            }else{//successor can be the current root or somenode in the left tree
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }
}
