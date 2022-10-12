import java.util.Deque;
import java.util.LinkedList;

public class LongestValidParentheses_LC32 {
    //!!!!sol1, from acwing, O(n), O(n)
    public int longestValidParentheses(String s) {
        if(s == null && s.length() == 0) return 0;
        Deque<Integer> stack = new LinkedList<>();
        char[] ary = s.toCharArray();
        int res = 0;
        int start = -1; //last index of the previous section，上一段的最后一位


        for(int i = 0; i < ary.length; i++){
            if(ary[i] == '(') stack.offerFirst(i);
            else{ //')'
                if(stack.isEmpty()){
                    start = i; //mark the previous section's last index
                }else{
                    stack.pollFirst();
                    if(stack.isEmpty()){
                        res = Math.max(i - start, res);
                    }else{
                        res = Math.max(i - stack.peekFirst(), res);
                    }
                }
            }
        }

        return res;
    }
}
