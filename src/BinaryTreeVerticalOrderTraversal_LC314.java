import java.util.*;

public class BinaryTreeVerticalOrderTraversal_LC314 {
    class Pair{
        int col;
        TreeNode node;
        Pair(int col, TreeNode node){
            this.col = col;
            this.node = node;
        }
    }
    //sol1, bfs, O(n), O(n)
     public List<List<Integer>> verticalOrder(TreeNode root) {
         List<List<Integer>> res = new ArrayList<>();
         if(root == null) return res;
         //column and list of node values
         Map<Integer, List<Integer>> map = new HashMap<>();
         //run bfs
         Deque<Pair> queue = new LinkedList<>();
         queue.offerFirst(new Pair(0, root));
         int[] range = new int[2];

         while(!queue.isEmpty()){

             Pair cur = queue.pollLast();
             List<Integer> curList = map.getOrDefault(cur.col, new ArrayList<Integer>());
             curList.add(cur.node.key);
             map.put(cur.col, curList);
             //update 0 min and 1 max
             range[0] = Math.min(range[0], cur.col);
             range[1] = Math.max(range[1], cur.col);
             if(cur.node.left != null){
                 queue.offerFirst(new Pair(cur.col - 1, cur.node.left));
             }
             if(cur.node.right != null){
                 queue.offerFirst(new Pair(cur.col + 1, cur.node.right));
             }


         }

         for(int i = range[0]; i <= range[1]; i++){
             res.add(map.get(i));
         }
         return res;
     }

    //sol2: from official solution, using treemap, O(nlogn) O(n)
    public List<List<Integer>> verticalOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        //column and list of node values
        SortedMap<Integer, List<Integer>> treeMap = new TreeMap<>((i1, i2) ->{
            return i1.compareTo(i2);
        });
        //run bfs
        Deque<Pair> queue = new LinkedList<>();
        queue.offerFirst(new Pair(0, root));
        int[] range = new int[2];

        while(!queue.isEmpty()){

            Pair cur = queue.pollLast();
            List<Integer> curList = treeMap.getOrDefault(cur.col, new ArrayList<Integer>());
            curList.add(cur.node.key);
            treeMap.put(cur.col, curList);
            //update 0 min and 1 max
            range[0] = Math.min(range[0], cur.col);
            range[1] = Math.max(range[1], cur.col);
            if(cur.node.left != null){
                queue.offerFirst(new Pair(cur.col - 1, cur.node.left));
            }
            if(cur.node.right != null){
                queue.offerFirst(new Pair(cur.col + 1, cur.node.right));
            }


        }

        for(Map.Entry<Integer, List<Integer>> entry : treeMap.entrySet()){
            res.add(entry.getValue());
        }
        return res;
    }


}
