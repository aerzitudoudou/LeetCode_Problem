import java.util.ArrayList;
import java.util.List;

public class CountGoodNodesInBinaryTree_LC1448 {
    //sol1, my, O(n), O(h)
    public int goodNodes1(TreeNode root) {
        //number of good nodes
        int[] res = new int[1];
        //current path previous max keyue
        List<Integer> max = new ArrayList<>();
        dfs(res, max, root);
        return res[0];

    }

    private void dfs(int[] res, List<Integer> max, TreeNode root){
        if(root == null) return;
        int size = max.size();
        if(size == 0 || root.key >= max.get(size - 1)){
            res[0]++;
            max.add(root.key);
            dfs(res, max, root.left);
            dfs(res, max, root.right);
            max.remove(max.size() - 1);
        }else{
            dfs(res, max, root.left);
            dfs(res, max, root.right);
        }

    }


    //!!!sol2, my, O(n), O(h), pass by keyue of the reference that changes globally. need to reset once call stack is back.
    public int goodNodes2(TreeNode root) {
        //number of good nodes
        int[] res = new int[1];
        //current path previous max keyue
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        dfs(res, max, root);
        return res[0];

    }

    private void dfs(int[] res, int[] max, TreeNode root){
        if(root == null) return;
        if( root.key >= max[0]){
            res[0]++;
            int orig = max[0];
            max[0] = root.key;
            dfs(res, max, root.left);
            dfs(res, max, root.right);
            max[0] = orig;

        }else{
            dfs(res, max, root.left);
            dfs(res, max, root.right);
        }

    }


    //!!!sol3, from https://leetcode.com/problems/count-good-nodes-in-binary-tree/discuss/635259/JavaC%2B%2BPython-One-line
    //pass by copy of the keyue, O(n), O(h)
    int res = 0;
    public int goodNodes3(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return res;
    }


    private void dfs(TreeNode root, int key){
        if(root == null) return;
        if(root.key >= key) res++;
        dfs(root.left, Math.max(root.key, key));
        dfs(root.right, Math.max(root.key, key));
    }


}
