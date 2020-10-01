package amazonOA;

import java.util.Deque;
import java.util.LinkedList;
//leetcode 1381
//https://leetcode.com/problems/design-a-stack-with-increment-operation/
/*
Use an additional array to record the increment value.
If increment operation is to add val to the bottom k elements in the stack, inc[k - 1] += val.
Only should we know the top element's value when doing pop. Under other cases, we only need to store each element's increment value.
When pop, the element back to the top of the stack was added inc[top]. After it, the inc value is passed down to inc[top - 1], to reduce the time complexity of increment to O(1)
* */
public class CustomStack {
    int n;
    int[] inc;
    Deque<Integer> stack;
    //T:O(N) S:O(N)
    public CustomStack(int maxSize) {
        n = maxSize;
        inc = new int[n]; //storing the increment value
        stack = new LinkedList<>();
    }
    //O(1) time and space
    public void push(int x) {
        if (stack.size() < n){
            stack.offerFirst(x);
        }
    }

    //O(1) time and space
    public int pop() {
        int i = stack.size() - 1;
        if (i < 0){
            return -1;
        }
        if (i > 0){
            inc[i - 1] += inc[i];
        }
        int res = stack.pollFirst() + inc[i];
        inc[i] = 0;
        return res;
    }
    //O(1) time and space
    public void increment(int k, int val) {
        int i = Math.min(k, stack.size()) - 1;
        if (i >= 0)
            inc[i] += val;
    }
}
