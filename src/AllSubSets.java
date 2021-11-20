import java.util.ArrayList;
import java.util.List;

public class AllSubSets {

    public List<String> subSets(String set) {
        List<String> res = new ArrayList<>();
        if(set == null) return res;
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, 0, set);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, int index, String set){
        if(set.length() == 0 || index == set.length()){
            res.add(sb.toString());
            return;
        }

        //add current letter
        sb.append(set.charAt(index));
        dfs(res, sb, index + 1, set);
        sb.deleteCharAt(sb.length() - 1);

        //without current letter
        dfs(res, sb, index + 1, set);
    }

    public List<String> subSetsI(String set) {

        List<String> res = new ArrayList<>();
        if(set == null){
            return res;
        }
        StringBuilder sb = new StringBuilder();

        char[] charAry = set.toCharArray();
        helper(res, 0, sb, charAry);
        return res;
    }

    private void helper(List<String> res, int index, StringBuilder sb, char[] charAry){
        if(index == charAry.length - 1){
            //snapshot: string is immutable
            sb.append(charAry[index]);
            res.add(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            return;
        }
        //[abc, ab, ac, a, bc, b, c, ]
        //[abc, ab, ac, a, bc, b, c, ]
//        if(index == charAry.length){
//            res.add(sb.toString());
//            return;
//        }

        sb.append(charAry[index]);
        helper(res, index + 1, sb, charAry);
        //does not delete the index one!!!!! nothing to do with index!!!! delete the last item
        sb.deleteCharAt(sb.length() - 1);

        helper(res, index + 1, sb, charAry);
    }
}
