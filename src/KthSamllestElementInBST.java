import java.util.Deque;
import java.util.LinkedList;
//lc 230
public class KthSamllestElementInBST {
    //bst inorder traverse is sorted and ascending
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.offerFirst(cur);
                cur = cur.left;
            }
            TreeNode tmp = stack.pollFirst();
            k--;
            if(k == 0) return tmp.key;
            cur = tmp.right;

        }
        return -1;
    }
}
