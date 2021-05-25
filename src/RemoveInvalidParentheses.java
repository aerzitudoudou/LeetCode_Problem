import java.util.ArrayList;
import java.util.List;

//lc 301
public class RemoveInvalidParentheses {
    //from HF: https://www.youtube.com/watch?v=NWAseBzZj-c
    //T: 2^n * n    S: n * n
    public List<String> removeInvalidParentheses(String s) {
        //calculate minimum character to be removed
        int leftCount = 0, res = 0;
        for(char c : s.toCharArray()){
            if(c == '(') leftCount++;
            else if(c == ')') {
                if(leftCount > 0) leftCount--;
                else res++;
            }
        }
        res += leftCount;
        int maxLen = s.length() - res;

        List<String> resList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(resList, 0, maxLen, 0, sb, s);
        return resList;
    }

    /*
            i
        ( ) ( )  )  ( )
 count  1 0 1 0 -1

    */
    private void dfs(List<String> resList, int index, int maxLen, int count, StringBuilder sb, String s){
        //pruning
        if(count < 0) return;
        if(sb.length() > maxLen) return;
        //base case
        if(index == s.length()){
            if(sb.length() == maxLen && count == 0) {
                resList.add(sb.toString());
            }
            return;
        }
        /*recursion

        if('(' or ')')
            if cur char == last char of sb  : append
            else :  append or not append
        else
            append

            (((( ))
            ((

            C42 = 6




          e.g. BAA(all subsets II)

                                                    #
                                                   /\
   index = 0                                      B  #
                                                 /\  /\


   index = 1                                   BA B  A   #

                                               /  /\ \   /\

   index = 2                                 BAA BA B AA A #

          }
        */
        char c = s.charAt(index);
        if(c == '(' || c == ')'){
            //not append: cur char != last character of sb
            if(sb.length() == 0 || c != sb.charAt(sb.length() - 1)){
                dfs(resList, index + 1, maxLen, count, sb, s);
            }
            //always append
            dfs(resList, index + 1, maxLen, c == '(' ? count + 1 : count - 1, sb.append(c), s);
            sb.deleteCharAt(sb.length() - 1);

        }else{
            dfs(resList, index + 1, maxLen, count, sb.append(c), s);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
