import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubSetII {
    //sol1, from lai, O(nlogn + 2^n * n), O(n)
    public List<String> subSets(String set) {
        List<String> res = new ArrayList<>();
        if(set == null) return res;
        char[] ary = set.toCharArray();
        Arrays.sort(ary);
        StringBuilder sb = new StringBuilder();
        dfs(sb, ary, res, 0);
        return res;

    }

    private void dfs(StringBuilder sb, char[] ary, List<String> res, int index){
        //base case
        if(index == ary.length){
            res.add(sb.toString());
            return;
        }

        //add
        sb.append(ary[index]);
        dfs(sb, ary, res, index + 1);
        sb.deleteCharAt(sb.length() - 1);



        //not add
        index++;
        while(index < ary.length && ary[index] == ary[index - 1]){
            index++;
        }
        dfs(sb, ary, res, index);

    }
}
