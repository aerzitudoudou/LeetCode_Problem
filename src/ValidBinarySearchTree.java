import java.util.Deque;
import java.util.LinkedList;
//lc 98
public class ValidBinarySearchTree {



    //!!!!!!!!sol 1: bfs in order traversal is a sorted increasing array
    //T: N S: H
    //pre represents the previous in order traverse treenode
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null, cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.offerFirst(cur);
                cur = cur.left;
            }
            TreeNode tmp = stack.pollFirst();
            if(pre != null && pre.key >=  tmp.key) return false;
            pre = tmp;
            cur = tmp.right;

        }
        return true;
    }

    //!!!!sol 2: concise recursive way
    public boolean isValidBST3(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode cur, Integer min, Integer max){
        if(cur == null) return true;
        return (min == null || cur.key > min) && (max == null || cur.key < max) && helper(cur.left, min, cur.key) && helper(cur.right, cur.key, max);
    }

    //my way T:N S: h
    public boolean isValidBST(TreeNode root) {
        boolean[] flag = new boolean[1];
        flag[0] = true;
        helper(root, flag);
        return flag[0];
    }
    //Integer[] represent the min and max value of subtree

    private Integer[] helper(TreeNode root, boolean[] flag){
        if(root == null) return new Integer[]{null, null};
        Integer[] minMax = {root.key, root.key};

        if(root.left != null){
            Integer[] leftMinMax = helper(root.left, flag);
            if(leftMinMax[1] >= minMax[0]){
                flag[0] = false;
            }
            minMax[0] = Math.min(root.key, leftMinMax[0]);
        }

        if(root.right != null){
            Integer[] rightMinMax = helper(root.right, flag);
            if(rightMinMax[0] <= minMax[1]){
                flag[0] = false;
            }
            minMax[1] = Math.max(root.key, rightMinMax[1]);
        }
        return minMax;

    }







}
