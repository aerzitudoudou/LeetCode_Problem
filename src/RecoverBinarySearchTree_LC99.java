public class RecoverBinarySearchTree_LC99 {
    //sol1,from wisdompeak, O(n), O(h)
    // in order traversal, find a1 > a2 && a3 > a4. a1 a4 is the node to be switched
    //corner case: only have a1 > a2, switch
    //
    TreeNode first = null, second = null, lastSeen = null;
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
}
