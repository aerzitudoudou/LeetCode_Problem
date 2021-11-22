import java.util.ArrayList;
import java.util.List;

public class AllPermutationsI_LAI64 {
    //sol1, my, O(n! * n), O(n)
    public List<String> permutations(String input) {
        char[] ary = input.toCharArray();
        List<String> res = new ArrayList<>();
        dfs(0, ary, res);
        return res;
    }

    private void dfs(int index, char[] ary, List<String> res){
        if(index == ary.length){
            res.add(new String(ary));
            return;
        }

        //index max =  ary.length - 1
        for(int i = index; i < ary.length; i++){
            swap(ary, index, i);
            dfs(index + 1, ary, res);
            swap(ary, index, i);
        }

    }


    private void swap(char[] ary, int a, int b){
        char tmp = ary[a];
        ary[a] = ary[b];
        ary[b] = tmp;
    }

}
