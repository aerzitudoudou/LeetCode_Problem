import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AllValidParenthesesII {
    public List<String> validParentheses(int l, int m, int n) {
        //一共有多少层： (l + m + n) * 2
        //每个层分几个叉： l + m + n
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] p = {'(',')','<','>','{','}'}; //create the branches
        int[] remain = {l, l, m, m, n, n};
        int level = 2 * (l + m + n);
        Deque<Character> stack = new LinkedList(); //判断右括号是不是能放进去,recursion tree 自上向下vertical传递，是全局的变量，like sb. 不是per level的
        dfs(res, sb, p, remain, level, stack, 0);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, char[] p, int[] remain, int level, Deque<Character> stack, int index){
        if(index == level){
            res.add(sb.toString());
            return;
        }
        for(int i = 0; i < p.length; i++){
            //create branch with (, <, or {
            if(i % 2 == 0 ){
                if(remain[i] > 0){
                    sb.append(p[i]);
                    remain[i]--;
                    stack.offerFirst(p[i]);
                    dfs(res, sb, p, remain, level, stack, index + 1);
                    stack.pollFirst();
                    remain[i]++;
                    sb.deleteCharAt(sb.length() - 1);
                }
            }else{//create branch with ), >, or }
                if(!stack.isEmpty() && stack.peekFirst().equals(p[i - 1]) && remain[i] > 0){
                    sb.append(p[i]);
                    remain[i]--;
                    stack.pollFirst();
                    dfs(res, sb, p, remain, level, stack, index + 1);
                    remain[i]++;
                    sb.deleteCharAt(sb.length() - 1);
                    stack.offerFirst(p[i - 1]);
                }

            }
        }
    }
}
