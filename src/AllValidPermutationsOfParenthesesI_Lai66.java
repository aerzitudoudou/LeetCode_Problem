import java.util.ArrayList;
import java.util.List;

public class AllValidPermutationsOfParenthesesI_Lai66 {
        //way 1, my, O(2^2n * n), O(n)
        public List<String> validParentheses(int n) {
            List<String> res = new ArrayList<>();
            int unusedLeft = n, unusedRight = n;
            StringBuilder sb = new StringBuilder();
            dfs(res, unusedLeft, unusedRight, sb, 0, n);
            return res;
        }


        private void dfs(List<String> res, int unusedLeft, int unusedRight, StringBuilder sb, int index, int n){
            if(index == 2 * n){
                res.add(sb.toString());
                return;
            }

            //add right p
            if(unusedRight > 0 && unusedRight > unusedLeft){
                sb.append(')');
                dfs(res, unusedLeft, unusedRight - 1, sb, index + 1, n);
                sb.deleteCharAt(sb.length() - 1);
            }

            //add left p
            if(unusedLeft > 0){
                sb.append('(');
                dfs(res, unusedLeft - 1, unusedRight, sb, index + 1, n);
                sb.deleteCharAt(sb.length() - 1);
            }

        }


}
