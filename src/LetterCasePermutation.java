import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    //myself way: dfs T:2 ^ n   S: n
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, S, 0);
        return res;

    }


    private void dfs(List<String> res, StringBuilder sb, String s, int index){
        if(index == s.length()){
            res.add(sb.toString());
            return;
        }

        if(Character.isLetter(s.charAt(index))){
            sb.append(Character.toLowerCase(s.charAt(index)));
            dfs(res, sb, s, index + 1);
            sb.deleteCharAt(sb.length() - 1);

            sb.append(Character.toUpperCase(s.charAt(index)));
            dfs(res, sb, s, index + 1);
            sb.deleteCharAt(sb.length() - 1);

        }else{
            sb.append(s.charAt(index));
            dfs(res, sb, s, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
