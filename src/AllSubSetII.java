import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubSetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //subsetII
        //加的时候一样加，不加的时候跳过所有的重复，从下一个不重复的字母做起
        List<List<Integer>> res = new ArrayList<>();

        if(nums == null){
            return res;
        }

        Arrays.sort(nums);
        List<Integer> sb = new ArrayList<>();
        int index = 0;
        dfs(sb, res, index, nums);
        return res;
    }

    private void dfs(List<Integer> sb, List<List<Integer>> res, int index, int[] nums){
        if(index == nums.length){
            res.add(new ArrayList(sb));
            return;
        }
        int tmp = nums[index];
        sb.add(tmp);
        dfs(sb, res, index + 1, nums);
        sb.remove(sb.size() - 1);
        while(index + 1 < nums.length && nums[index] == nums[index + 1]){
            index++;
        }

        dfs(sb, res, index + 1, nums);


    }
}
