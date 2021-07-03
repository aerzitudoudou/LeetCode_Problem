import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerticalOrderTraversalOfABinaryTree_LC987 {
    //sol1, my, O(nlogn), O(n)
    class Position{
        int val;
        int row;
        int col;
        Position(int val, int row, int col){
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Position> list = new ArrayList<>();
        dfs(list, root, 0, 0);
        Collections.sort(list, (p1, p2) ->{
            if(p1.col == p2.col){
                if(p1.row == p2.row){
                    return p1.val - p2.val;
                }else{
                    return p1.row - p2.row;
                }
            }else{
                return p1.col - p2.col;
            }
        });

        //index i represents the index of the 1st node with a new column
        for(int i = 0; i < list.size(); i++){
            List<Integer> cur = new ArrayList<>();
            while(i < list.size() - 1 && list.get(i).col == list.get(i + 1).col){
                cur.add(list.get(i).val);
                i++;
            }
            cur.add(list.get(i).val);
            res.add(cur);
        }
        return res;


    }



    private void dfs(List<Position> list, TreeNode root, int row, int col){
        if(root == null) return;
        Position cur = new Position(root.key, row, col);
        list.add(cur);
        dfs(list, root.left, row + 1, col - 1);
        dfs(list, root.right, row + 1, col + 1);
    }

}
