public class RecoverBinarySearchTree_LC99 {
    //sol1,from wisdompeak, O(n), O(h)
    // in order traversal, find a1 > a2 && a3 > a4. a1 a4 is the node to be switched
    //corner case: only have a1 > a2, switch

    TreeNode first = null, second = null, lastSeen = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {

        dfs(root);
        int tmp = first.key;
        first.key = second.key;
        second.key = tmp;
    }


    private void dfs(TreeNode root){
        if(root == null) return;
        //in order traversal
        dfs(root.left);

        //do something on the current node
        if(lastSeen != null && root.key < lastSeen.key){
            second = root;
            if(first == null){
                first = lastSeen;
            }else{
                return;
            }
        }
        lastSeen = root;


        dfs(root.right);
    }


    //sol2, use TreeNode[] to change the node from a global view
    public void recoverTree2(TreeNode root) {
        TreeNode[] lastSeen = new TreeNode[]{new TreeNode(Integer.MIN_VALUE)};
        TreeNode[] first = new TreeNode[1];
        TreeNode[] second = new TreeNode[1];
        dfs(root, lastSeen, first, second);
        int tmp = first[0].key;
        first[0].key = second[0].key;
        second[0].key = tmp;
    }

    private void dfs(TreeNode root, TreeNode[] lastSeen, TreeNode[] first, TreeNode[] second){
        if(root == null) return;
        dfs(root.left, lastSeen, first, second);

        //do something
        if(first[0] == null && root.key < lastSeen[0].key){
            first[0] = lastSeen[0];
            second[0] = root;
        }else if(first[0] != null && root.key < lastSeen[0].key){
            second[0] = root;
            return;
        }
        lastSeen[0] = root;

        dfs(root.right, lastSeen, first, second);
    }
}
