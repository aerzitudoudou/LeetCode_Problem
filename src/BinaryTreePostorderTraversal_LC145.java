import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePostorderTraversal_LC145 {
    //sol1, recursive, O(n), O(h)
     public List<Integer> postorderTraversal_1(TreeNode root) {
         //sol1, my, recursive, O(n),O(h)
         List<Integer> res = new ArrayList<>();
         dfs(root, res);
         return res;

     }

     private void dfs(TreeNode root, List<Integer> res){
         if(root == null) return;
         dfs(root.left, res);
         dfs(root.right, res);
         res.add(root.key);
     }
    //!!!sol2, iterative, from Lai,  O(n), O(h)
     public List<Integer> postorderTraversal_2(TreeNode root) {
         List<Integer> res = new ArrayList<>();
         Deque<TreeNode> stack = new LinkedList<>();
         if(root == null) return res;
         TreeNode pre = null;
         stack.offerFirst(root);
         while(!stack.isEmpty()){
             //if previous is null -> going down(left subtree has priority)
             //if previous is current's parent -> going down, left subtree has priority
             TreeNode cur = stack.peekFirst();
             if(pre == null || cur == pre.left || cur == pre.right){
                 if(cur.left != null) stack.offerFirst(cur.left);
                 else if(cur.right != null) stack.offerFirst(cur.right);
                 else{
                     res.add(cur.key);
                     stack.pollFirst();
                 }

             }else if(pre == cur.left){//if previous == cur.left -> left tree is finished , going current.right
                 if(cur.right != null) stack.offerFirst(cur.right);
                 else{
                     res.add(cur.key);
                     stack.pollFirst();
                 }
             }else{//if previous == cur.right -> right tree is finished, pop current, going up
                 res.add(cur.key);
                 stack.pollFirst();
             }

             pre = cur;
         }

         return res;



     }

    //!!!sol3, iterative, post order <left, right , root> is the reverse of preorder: <root, right, left>,O(n),O(n)
    public List<Integer> postorderTraversal_3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if(root == null) return res;
        stack.offerFirst(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollFirst();
            res.add(0, cur.key);
            if(cur.left != null) stack.offerFirst(cur.left);
            if(cur.right != null) stack.offerFirst(cur.right);
        }
        return res;
    }
}
