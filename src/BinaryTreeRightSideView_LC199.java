import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeRightSideView_LC199 {
    //!!sol1, dfs, O(n), O(h) https://www.youtube.com/watch?v=f72I2qz9K7k
     public List<Integer> rightSideView1(TreeNode root) {
         List<Integer> res = new ArrayList<>();
         dfs(root, res, 0);
         return res;
     }

     private void dfs(TreeNode root, List<Integer> res, int level){
         if(root == null) return;
         if(level == res.size()){
             res.add(root.key);
         }
         dfs(root.right, res, level + 1);
         dfs(root.left, res, level + 1);
     }


    //!!!sol2: bfs O(n), O(n) https://www.youtube.com/watch?v=f72I2qz9K7k
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int a = 0; a < size; a++){
                TreeNode cur = queue.pollLast();
                if(a == size - 1){
                    res.add(cur.key);
                }
                if(cur.left != null) queue.offerFirst(cur.left);
                if(cur.right != null) queue.offerFirst(cur.right);
            }

        }

        return res;
    }

}
