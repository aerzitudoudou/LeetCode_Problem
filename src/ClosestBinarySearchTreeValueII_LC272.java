import java.util.LinkedList;
import java.util.List;

public class ClosestBinarySearchTreeValueII_LC272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {

        LinkedList<Integer> res = new LinkedList<>(); //both a list and a deque if define as a LinkedList<> Type
        helper(res, root, target, k);
        return res;
    }

    //!!!T:O(n)
    //S: Max(k, h)
    private void helper(LinkedList res, TreeNode root, double target, int k){
        if(root == null) return;
        //in order traversal
        helper(res, root.left, target, k);
        //if top of the queue - target < node.val - target: node.val -target will get larger and larger, stop
        //else enqueue the node
        if(res.size() < k) {
            res.offerFirst(root.key);
        }else{
            if(Math.abs(root.key - target) < Math.abs((int)res.peekLast() - target)){
                res.pollLast();
                res.offerFirst(root.key);
            }else return;

        }

        helper(res, root.right, target, k);

    }
}
