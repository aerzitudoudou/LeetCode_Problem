import java.util.ArrayList;
import java.util.List;

public class Combinations {
    //leetcode 77
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        if(n == 0){
            return list;
        }
        int index = 0;
        List<Integer> nums = new ArrayList<>();
        for(int i = 0; i < n; i++){
            nums.add(i + 1);
        }
        List<Integer> node = new ArrayList<>();
        dfs(nums, list, 0, node, k);
        return list;
    }

    private void dfs(List<Integer> nums, List<List<Integer>> list, int index, List<Integer> node, int k){
        //base case 1: current node length is k, add to result and return
        if(node.size() == k){
            list.add(new ArrayList<>(node));
            return;
        }

        //base case 2: current node reaches the bottom layer of the recursion tree, return
        if(index == nums.size()){
            return;
        }

        node.add(nums.get(index));
        dfs(nums, list, index + 1, node, k);
        node.remove(node.size() - 1);
        dfs(nums, list, index + 1, node, k);


    }
}
