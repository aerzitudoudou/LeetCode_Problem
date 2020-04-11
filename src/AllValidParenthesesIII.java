import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
//T:O(6^(l + m + n))
//S:O(l + m + n)
public class AllValidParenthesesIII {
    public List<String> validParenthesesIII(int l, int m, int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int[] remain = {l, l, m, m, n, n};
        char[] p = {'(',')','<','>','{','}'};
        int level = 2 * (l + m + n);
        Deque<Character> stack = new LinkedList<>();
        dfs(res, sb, remain, p, level, stack);
        return res;
    }

    private void dfs(List<String> res,  StringBuilder sb, int[] remain, char[] p, int level, Deque<Character> stack){
        if(sb.length() == level){
            res.add(sb.toString());
            return;
        }
        for(int i = 0; i < remain.length; i++){
            //left
            if(i % 2 == 0){
                if(remain[i] > 0 && match(p[i], stack)){
                    stack.offerFirst(p[i]);
                    remain[i]--;
                    sb.append(p[i]);
                    dfs(res, sb, remain, p, level, stack);
                    sb.deleteCharAt(sb.length() - 1);
                    remain[i]++;
                    stack.pollFirst();
                }
            }else{
                if(remain[i] > 0 && !stack.isEmpty() && stack.peekFirst().equals(p[i - 1])){
                    stack.pollFirst();
                    remain[i]--;
                    sb.append(p[i]);
                    dfs(res, sb, remain, p, level, stack);
                    sb.deleteCharAt(sb.length() - 1);
                    remain[i]++;
                    stack.offerFirst(p[i - 1]);
                }
            }
        }
    }

    private boolean match(char a, Deque<Character> stack){//{{}} 不valid 同级不能嵌套
        if(stack.isEmpty()){
            return true;
        }
        if(stack.peekFirst() == '{' && (a == '<' || a == '(')) return true;
        else if(stack.peekFirst() == '<' && a == '(') return true;
        else if(stack.peekFirst() == '(' ) return false;
        return false;
    }
}
