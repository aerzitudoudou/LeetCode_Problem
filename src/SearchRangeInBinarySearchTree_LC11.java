import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SearchRangeInBinarySearchTree_LC11 {
    //sol1, my, O(n), O(h)
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        //in order traverse to get the ascending order of the bst
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){//!!!in order traverse: condition is OR, not AND!!!
            while(cur != null){
                stack.offerFirst(cur);
                cur = cur.left;
            }
            TreeNode tmp = stack.pollFirst();
            if(tmp.key >= k1 && tmp.key <= k2) res.add(tmp.key);
            cur = tmp.right;
        }

        return res;
    }
}
