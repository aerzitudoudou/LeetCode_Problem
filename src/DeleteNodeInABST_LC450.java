public class DeleteNodeInABST_LC450 {
    //sol1, from Lai, O(h), O(h)
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.key < key){
            root.right = deleteNode(root.right, key);
            return root;
        }else if(root.key > key){
            root.left = deleteNode(root.left, key);
            return root;
        }else{
            //has only left child tree or right child tree
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }else{//has both left child and right child, find the closest larger node on the right tree and make it new root
                //right tree root doesn't have left child
                if(root.right.left == null){
                    root.right.left = root.left;
                    return root.right;
                }else{//right tree root has left child
                    TreeNode smallestNode = findSmallestNode(root.right);
                    smallestNode.left = root.left;
                    smallestNode.right = root.right;
                    return smallestNode;
                }
            }

        }
    }

    //find smallest Node & adjust the smallest.right to make it left child of it's parent
    private TreeNode findSmallestNode(TreeNode cur){
        TreeNode pre = cur;
        cur = cur.left;
        while(cur.left != null){
            pre = cur;
            cur = cur.left;
        }
        pre.left = cur.right;
        return cur;
    }

}
