import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal_LC103 {
    //sol1.1 my, O(n), O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root);
        boolean isOdd = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.pollLast();

                if(isOdd){
                    list.add(tmp.key);
                }else{
                    //use linkedlist to make this operation cost O(1)
                    list.add(0, tmp.key);
                }
                if(tmp.left != null){
                    queue.offerFirst(tmp.left);
                }
                if(tmp.right != null){
                    queue.offerFirst(tmp.right);
                }
                if(i == size - 1){
                    isOdd = !isOdd;
                }
            }

            res.add(list);

        }

        return res;

    }

//     //sol1.2, acwing, almost same approach expect reverse all at once, O(n), O(n)
     public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
         List<List<Integer>> res = new ArrayList<>();
         if(root == null) return res;
         Deque<TreeNode> queue = new LinkedList<>();
         queue.offerFirst(root);
         int count = 1;
         while(!queue.isEmpty()){
             int size = queue.size();
             List<Integer> list = new ArrayList<>();
             for(int i = 0; i < size; i++){
                 TreeNode tmp = queue.pollLast();
                 list.add(tmp.key);
                 if(tmp.left != null){
                     queue.offerFirst(tmp.left);
                 }
                 if(tmp.right != null){
                     queue.offerFirst(tmp.right);
                 }
             }
             if(count % 2 == 0){
                 Collections.reverse(list);
             }
             res.add(list);
             count++;

         }

         return res;

     }
}
