import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LevelOrderTraverse {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> q = new LinkedList<>();
        //放的时候要放integer
        q.offerFirst(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> array = new ArrayList<>();
            for(int i = 0; i < size; i++){
                //expand
                TreeNode tmp = q.pollLast();
                array.add(tmp.key);

                //generate
                if(tmp.left != null){
                    q.offerFirst(tmp.left);
                }
                if(tmp.right != null){
                    q.offerFirst(tmp.right);
                }

            }
            res.add(array);

        }
        return res;
    }
}
