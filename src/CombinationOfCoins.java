import java.util.ArrayList;
import java.util.List;

public class CombinationOfCoins {
    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(target, coins, 0, res, cur, 0);
        return res;

    }

    private void helper(int target, int[] coins, int index, List<List<Integer>> res, List<Integer> cur, int sum){
        if(index == coins.length){
            if(sum == target){
                res.add(new ArrayList(cur));
            }
            return;
        }

        for(int i = 0; (sum + i * coins[index]) <= target; i++){
            cur.add(i);
            helper(target, coins, index + 1, res, cur, sum + i * coins[index]);
            cur.remove(cur.size() - 1);
        }

    }


}
