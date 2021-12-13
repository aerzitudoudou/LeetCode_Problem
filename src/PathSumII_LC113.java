import java.util.ArrayList;
import java.util.List;

public class PathSumII_LC113 {
    //sol1, my, dfs, O(n), O(h)
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(root, res, 0, target, list);
        return res;

    }


    private void dfs(TreeNode root, List<List<Integer>> res, int preSum, int target, List<Integer> list){
        if(root == null) return;


        list.add(root.key);

        if(root.left == null && root.right == null && root.key + preSum == target){
            res.add(new ArrayList(list));
        }

        if(root.left != null){
            dfs(root.left, res, preSum + root.key, target, list);
        }

        if(root.right != null){
            dfs(root.right, res, preSum + root.key, target, list);
        }
        list.remove(list.size() - 1);
    }

}
