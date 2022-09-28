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


//!!!sol1. from huifeng: https://www.youtube.com/watch?v=DQezNhctk-Y O(n), O(h)

    class BSTIterator {

        Deque<TreeNode> stack = new LinkedList<>();
        //O(h), O(h)
        public BSTIterator(TreeNode root) {
            pushLeftBranchNodes(root);
        }
        //amortized Time: O(1), worst case: O(n).
        //from https://leetcode.com/problems/binary-search-tree-iterator/discuss/52525/My-solutions-in-3-languages-with-Stack:
        //The average time complexity of next() function is O(1) indeed in your case.
        // As the next function can be called n times at most, and the number of right nodes in self.pushAll(tmpNode.right) function is maximal n in a tree which has n nodes, so the amortized time complexity is O(1).
        //Space: O(h)
        public int next() {
            if(hasNext()){
                TreeNode cur = stack.pollFirst();
                pushLeftBranchNodes(cur.right);
                return cur.key;
            }
            return -1;
        }

        //O(1), O(h)
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        //T: amortized: O(1), worst case : O(n)
        //S: O(h)
        private void pushLeftBranchNodes(TreeNode root){
            while(root != null){
                stack.offerFirst(root);
                root = root.left;
            }
        }


    }

// //sol2.1: my, O(n), O(n)
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

//sol2.2, my, iteratively do in order traverse, O(n), O(n)
//     Deque<TreeNode> stack = new LinkedList<>();
//     Deque<TreeNode> queue = new LinkedList<>();
//     //O(n), O(n)
//     public BSTIterator(TreeNode root) {
//         if(root != null){
//             TreeNode cur = root;
//             while(cur != null || !stack.isEmpty()){
//                 while(cur != null){
//                     stack.offerFirst(cur);
//                     cur = cur.left;
//                 }
//                 TreeNode tmp = stack.pollFirst();
//                 queue.offerFirst(tmp);
//                 cur = tmp.right;
//             }
//         }
//     }
//     //O(1), O(n)
//     public int next() {
//         return queue.pollLast().val;
//     }

//     //O(1), O(n)
//     public boolean hasNext() {
//         return !queue.isEmpty();
//     }


/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
