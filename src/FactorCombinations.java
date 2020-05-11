import java.util.ArrayList;
import java.util.List;
/**
 *
 *404. Factor Combinations
 * Medium
 * Given an integer number, return all possible combinations of the factors that can multiply to the target number.
 *
 * Example
 *
 * Give A = 24
 *
 * since 24 = 2 x 2 x 2 x 3
 *
 *               = 2 x 2 x 6
 *
 *               = 2 x 3 x 4
 *
 *               = 2 x 12
 *
 *               = 3 x 8
 *
 *               = 4 x 6
 *
 * your solution should return
 *
 * { { 2, 2, 2, 3 }, { 2, 2, 6 }, { 2, 3, 4 }, { 2, 12 }, { 3, 8 }, { 4, 6 } }
 *
 * note: duplicate combination is not allowed.
 *
 */

//T: M is the number of factors the target has, 能分出的最多的叉就是当factor 为2 的时候的叉数， 即2^n = target -> n = log2^ target 所以总的时间复杂度是recursion tree 叉数^ 层数 = O(log2 target)^M
//S: stack: O(M) + heap: O(M) M is the number is factors
//TODO: 在像汤老师确认如果直上直下带着一个list 空间复杂度怎么算

public class FactorCombinations {

    public List<List<Integer>> combinations(int target) {
        //这道题只考虑正的factor
        List<List<Integer>> res = new ArrayList<>();
        if(target <= 3){
            return res;
        }
        List<Integer> factors = new ArrayList<>();
        //find factors
        for(int i = 2; i < target; i++){
            if(target % i == 0){
                factors.add(i);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        //run dfs to find all the factor combinations
        dfs(target, factors, list, res, 0);
        return res;

    }

    private void dfs(int target, List<Integer> factors, List<Integer> list,  List<List<Integer>> res, int index){
        //加结果的时候可以反弹，是一个base case
        if(target == 1){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        //这个第一次写的时候忘了，到底了也算base case, 不然继续往下就会index out of bound 在        int factor = factors.get(index) 的时候
        if(index == factors.size()){
            return;
        }
        int factor = factors.get(index);


        for(int i = 0; (int)Math.pow(factor, i) <= target; i++){
            //可以整除，就可以叉出来一个叉
            if(target % (int)Math.pow(factor, i) == 0){
                //幂次i大于0的时候往结果里加i个factor
                int counter = i;
                while(counter-- > 0){
                    list.add(factor);
                }

                dfs((target / (int)Math.pow(factor, i)), factors, list, res, index + 1);
                counter = i;
                while(counter-- > 0){
                    list.remove(list.size() - 1);
                }
            }

        }



    }
}
