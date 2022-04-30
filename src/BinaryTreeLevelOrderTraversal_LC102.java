import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal_LC102 {
    //sol1, my, bfs, O(n), O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        if(root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();

        queue.offerFirst(root);

        while(!queue.isEmpty()){
            int len = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode curNode = queue.pollLast();
                list.add(curNode.key);

                if(curNode.left != null) queue.offerFirst(curNode.left);
                if(curNode.right != null) queue.offerFirst(curNode.right);
            }

            res.add(list);
        }

        return res;

    }
}
