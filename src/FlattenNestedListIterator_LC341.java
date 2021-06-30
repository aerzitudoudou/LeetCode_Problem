import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
//[[1,2],[3,4,[5,6]]]
/*Stack:
[3,4,[5,6]]  [1,2]
[3,4,[5,6]]  [2]  [1]
[1] out
[2] out
[5,6] [4] [3]
[3] out
[4] out
[6] [5]
[5] out
[6] out



*/
//from huifeng: https://www.youtube.com/watch?v=R2dohSHOWXQ
//todo: time and space complexity?
class FlatternNestedListIterator_LC341{

    public class NestedInteger{
        private Integer getInteger(){
            return null;
        }

        private boolean isInteger(){
            return true;
        }

        private List<NestedInteger> getList(){
            return null;
        }
    }
    public class NestedIterator implements Iterator<Integer> {

        Deque<NestedInteger> stack = new LinkedList<>();
        public NestedIterator(List<NestedInteger> nestedList) {
            for(int i = nestedList.size() - 1; i >= 0; i--){
                stack.offerFirst(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            if(hasNext()) return stack.pollFirst().getInteger();
            return null;
        }

        //!!!!check if stack is empty and if first ele is an integer, covers corner case:[[]]
        @Override
        public boolean hasNext() {
            while(!stack.isEmpty() && !stack.peekFirst().isInteger()){
                List<NestedInteger> list = stack.pollFirst().getList();
                for(int i = list.size() - 1; i >= 0; i--){
                    stack.offerFirst(list.get(i));
                }
            }
            return !stack.isEmpty();
        }
    }
}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */