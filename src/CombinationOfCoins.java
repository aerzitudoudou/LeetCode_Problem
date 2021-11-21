import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationOfCoins {
    //sol1, my, O(target ^ m), O(m), m is the length of coins
    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        Integer[] ary = new Integer[coins.length];
        Arrays.fill(ary, 0);
        dfs(res, 0, ary, coins, target);
        return res;
    }

    private void dfs(List<List<Integer>> res, int index, Integer[] ary, int[] coins, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(Arrays.asList(ary)));
            return;
        }
        if (index == coins.length) return;

        for (int i = 0; target - i * coins[index] >= 0; i++) {
            int tmp = ary[index];
            ary[index] = i;
            dfs(res, index + 1, ary, coins, target - i * coins[index]);
            ary[index] = tmp;

        }
    }

    //sol2, lai
    public List<List<Integer>> combinations2(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper2(target, coins, 0, res, cur, 0);
        return res;

    }

    private void helper2(int target, int[] coins, int index, List<List<Integer>> res, List<Integer> cur, int sum){
        if(index == coins.length){
            if(sum == target){
                res.add(new ArrayList(cur));
            }
            return;
        }

        for(int i = 0; (sum + i * coins[index]) <= target; i++){
            cur.add(i);
            helper2(target, coins, index + 1, res, cur, sum + i * coins[index]);
            cur.remove(cur.size() - 1);
        }

    }


}
