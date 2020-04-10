import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCombination {
    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> sol = new ArrayList<>();
        findCombination(res, coins, target, 0, sol);
        return res;
    }

    //index: level number
//sol: the array for saving the result for each base case
//coins: [25, 10, 5, 1]
    private void findCombination(List<List<Integer>> res, int[] coins, int moneyLeft, int index, List<Integer> sol){
        if(index == coins.length){
            if(moneyLeft == 0){
                List<Integer> tmp = new ArrayList(sol);
                res.add(tmp);
            }
            return;
        }
        for(int i = 0; i * coins[index] <= moneyLeft; i++){
            sol.add(i);
            findCombination(res, coins, moneyLeft - i * coins[index], index + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}
