import java.util.Deque;
import java.util.LinkedList;

public class BinarySearchTreeIterator_LC173 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
//!!!sol. from huifeng: https://www.youtube.com/watch?v=DQezNhctk-Y O(n), O(h)

    class BSTIterator {

        Deque<TreeNode> stack = new LinkedList<>();

        public BSTIterator(TreeNode root) {
            while(root != null){
                stack.offerFirst(root);
                root = root.left;
            }

        }

        public int next() {
            if(hasNext()) {
                TreeNode cur = stack.pollFirst();
                TreeNode sub = cur.right;
                while(sub != null){
                    stack.offerFirst(sub);
                    sub = sub.left;
                }
                return cur.key;
            }
            return -1;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }


    }

// //sol2: my, O(n), O(n)
// class BSTIterator {

//     Deque<TreeNode> queue = new LinkedList<>();

//     public BSTIterator(TreeNode root) {
//         inOrder(root, queue);
//     }

//     public int next() {
//         if(hasNext()) return queue.pollLast().val;
//         return -1;
//     }

//     public boolean hasNext() {
//         return queue.size() > 0;
//     }

//     private void inOrder(TreeNode root, Deque<TreeNode> queue){
//         if(root == null) return;
//         inOrder(root.left, queue);
//         queue.offerFirst(root);
//         inOrder(root.right, queue);
//     }
// }




/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
