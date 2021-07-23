import java.util.Deque;
import java.util.LinkedList;

public class NextGreaterElementII_LC503 {
    //sol1, my, O(n), O(n)
    //index: [i-2, 0] monotonic increasing stack
    //[i - 1, 0] monotonic increasing stack
     public int[] nextGreaterElements(int[] nums) {
         int l = nums.length;

         Deque<Integer> stack = new LinkedList<>();
         int[] res = new int[nums.length];
         for(int i = l - 2; i >= 0; i--){
             while(!stack.isEmpty() && stack.peekFirst() <= nums[i]){
                 stack.pollFirst();
             }
             stack.offerFirst(nums[i]);
         }

         for(int i = l - 1; i >= 0; i--){
             while(!stack.isEmpty() && stack.peekFirst() <= nums[i]){
                 stack.pollFirst();
             }
             if(stack.isEmpty()) res[i] = -1;
             else res[i] = stack.peekFirst();
             stack.offerFirst(nums[i]);
         }

         return res;


     }

    //ary: 1 2 4  length = l
    //circular ary: 1 2 4 1 2
    //!!!!sol2, same thought, base on the "imagine circular ary", use i%l to get the index of the number in the original ary
    //o(n), O(n)
    public int[] nextGreaterElements1(int[] nums) {
        int l = nums.length;
        int[] res = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 2 * l - 1; i >= 0; i--){
            while(!stack.isEmpty() && stack.peekFirst() <= nums[i % l]){
                stack.pollFirst();
            }
            if(i < l){
                if(stack.isEmpty()) res[i] = -1;
                else res[i] = stack.peekFirst();
            }
            stack.offerFirst(nums[i % l]);

        }

        return res;
    }

}
