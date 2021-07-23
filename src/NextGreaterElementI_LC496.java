import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NextGreaterElementI_LC496 {
    //sol1, my, O(one.length + two.length), O(two.length), monotonic increasing stack starting from right side
    public int[] nextGreaterElement(int[] one, int[] two) {
        //map: one number's <value, index>
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < one.length; i++){
            map.put(one[i], i);
        }

        int[] res = new int[one.length];
        //stack for saving first greater element's index, monotonic increasing stack
        Deque<Integer> stack = new LinkedList<>();
        for(int i = two.length - 1; i >= 0; i--){
            int num = two[i];
            //stack saves idx not value!!!
            while(!stack.isEmpty() && two[stack.peekFirst()] <= two[i]){
                stack.pollFirst();
            }

            if(map.containsKey(num)){
                int oneIndex = map.get(num);
                if(stack.isEmpty()) res[oneIndex] = -1;
                else res[oneIndex] = two[stack.peekFirst()];
            }
            stack.offerFirst(i);
        }

        return res;

    }

}
