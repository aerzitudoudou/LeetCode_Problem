import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreOrderTraversalIterative_LC144 {
    //sol1, my, O(n), O(h)
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollFirst();
            res.add(cur.key);
            if(cur.right != null) stack.offerFirst(cur.right);
            if(cur.left != null) stack.offerFirst(cur.left);
        }
        return res;
    }
}
