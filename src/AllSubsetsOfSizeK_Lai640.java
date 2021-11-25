import java.util.ArrayList;
import java.util.List;

public class AllSubsetsOfSizeK_Lai640 {
    //sol1, my, O(2^n * k), O(n)
    public List<String> subSetsOfSizeK(String set, int k) {


        List<String> res = new ArrayList<>();
        char[] ary = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        dfs(res, ary, sb, k, 0);
        return res;

    }


    private void dfs(List<String> res, char[] ary, StringBuilder sb, int k, int index){
        if(sb.length() == k){
            res.add(sb.toString());
            return;
        }

        if(index == ary.length){
            return;
        }

        //add cur element
        sb.append(ary[index]);
        dfs(res, ary, sb, k, index + 1);
        sb.deleteCharAt(sb.length() - 1);

        //don't add cur element
        dfs(res, ary, sb, k, index + 1);



    }
}
