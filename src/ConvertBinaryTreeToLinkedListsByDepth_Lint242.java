import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ConvertBinaryTreeToLinkedListsByDepth_Lint242 {
    //!!sol1, bfs, my, O(n), O(n)
     public List<ListNode> binaryTreeToLists1(TreeNode root) {
         List<ListNode> res = new ArrayList<>();
         if(root == null) return res;
         Deque<TreeNode> queue = new LinkedList<>();
         queue.offerFirst(root);
         while(!queue.isEmpty()){
             int size = queue.size();
             ListNode head = new ListNode(-1);
             ListNode cur = head;
             for(int i = 0; i < size; i++){
                 TreeNode tmp = queue.pollLast();
                 cur.next = new ListNode(tmp.key);
                 cur = cur.next;
                 if(tmp.left != null) queue.offerFirst(tmp.left);
                 if(tmp.right != null) queue.offerFirst(tmp.right);
             }

             res.add(head.next);

         }

         return res;
     }


    //sol2, dfs, my, O(n), O(n), first left then right , maintain a list of cur tail node for each level
      public List<ListNode> binaryTreeToLists2(TreeNode root) {
         List<ListNode> res = new ArrayList<>();
         if(root == null) return res;
         List<ListNode> curNodeOfLevel = new ArrayList<>();
         dfs1(res, root, 0, curNodeOfLevel);
         return res;

      }


      private void dfs1(List<ListNode> res, TreeNode root, int level,  List<ListNode> curNodeOfLevel){
          if(root == null) return;
          if(res.size() == level){
              ListNode head = new ListNode(root.key);
              curNodeOfLevel.add(head);
              res.add(head);
          }else{
              ListNode cur = curNodeOfLevel.get(level);
              cur.next = new ListNode(root.key);
              cur = cur.next;
              curNodeOfLevel.set(level, cur);
          }

          dfs1(res, root.left, level + 1, curNodeOfLevel);
          dfs1(res, root.right, level + 1, curNodeOfLevel);
      }

    //!!sol3, dfs, right first then left, always add cur as head, update head in res, O(n), O(n)
    public List<ListNode> binaryTreeToLists3(TreeNode root) {
        List<ListNode> res = new ArrayList<>();
        if(root == null) return res;
        dfs2(res, root, 0);
        return res;

    }

    private void dfs2(List<ListNode> res, TreeNode root, int level){
        if(root == null) return;
        //add the last node
        ListNode cur = new ListNode(root.key);
        if(res.size() == level){
            res.add(cur);
        }else{
            cur.next = res.get(level);
            res.set(level, cur);
        }

        dfs2(res, root.right, level + 1);
        dfs2(res, root.left, level + 1);
    }
}
