import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//lc 94
public class BinaryTreeInorderTraversal {
    //cur: cur treenode to be added to the stack
    //stack: if treenode has left childnode , push to stack. otherwise pop

    //O(n), O(h)
    public List<Integer> inorderTraversal(TreeNode root) {

        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.offerFirst(cur);
                cur = cur.left;
            }
            TreeNode tmp = stack.pollFirst();
            res.add(tmp.key);
            cur = tmp.right;
        }
        return res;

    }


}
