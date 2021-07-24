import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures_LC739 {
    //sol1, my, monotonic increasing stack from end to beginning, O(n),O(n)
    public int[] dailyTemperatures(int[] t) {
        int[] res = new int[t.length];
        Deque<Integer> stack = new LinkedList<>();
        for(int i = t.length - 1; i >= 0; i--){
            while(!stack.isEmpty() && t[stack.peekFirst()] <= t[i]){
                stack.pollFirst();
            }
            if(stack.isEmpty()) res[i] = 0;
            else res[i] = stack.peekFirst() - i;
            stack.offerFirst(i);

        }
        return res;
    }
}
