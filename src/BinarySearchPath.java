import java.util.ArrayList;
import java.util.List;

public class BinarySearchPath {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(root == null){
            return res;
        }
        sb.append(root.key);
        sb.append("->");
        helper(sb, res, root);


        return res;


    }


    private void helper (StringBuilder sb, List<String> res, TreeNode root){
        if(root.left == null && root.right == null){
            sb.delete(sb.length() - 2, sb.length());
            res.add(sb.toString());
        }

        if(root.left != null){
            sb.append(root.left.key);
            sb.append("->");
            helper(sb, res, root.left);
            sb.delete(sb.length() - 3, sb.length());

        }
        if(root.right != null){
            sb.append(root.right.key);
            sb.append("->");
            helper(sb, res, root.right);
            sb.delete(sb.length() - 3, sb.length());

        }

    }
}
