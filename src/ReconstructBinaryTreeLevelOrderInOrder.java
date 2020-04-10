import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeLevelOrderInOrder {
    public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inOrder.length; i++){
            map.put(inOrder[i], i);
        }
        return reconstruct(levelOrder, map, 0, inOrder.length - 1);
    }

    private TreeNode reconstruct(int[] levelOrder, Map<Integer, Integer> map, int inLeft, int inRight){
        if(inLeft > inRight){
            return null;
        }
        TreeNode root = new TreeNode(levelOrder[0]);
        int inMid = map.get(levelOrder[0]);
        int[] leftLevel = new int[inMid - inLeft];
        int[] rightLevel = new int[inRight - inMid];
        for(int i  = 1, j = 0, k = 0; i < levelOrder.length; i++){
            if(map.get(levelOrder[i]) < inMid){
                leftLevel[j++] = levelOrder[i];
            }else{
                rightLevel[k++] = levelOrder[i];
            }
        }
        root.left = reconstruct(leftLevel, map, inLeft, inMid - 1);
        root.right =  reconstruct(rightLevel, map, inMid + 1, inRight);
        return root;
    }
}
