import java.util.Deque;
import java.util.LinkedList;

//T: O(n)
//S: O(n)
public class isValidParentheses {
    public boolean isValidParentheses(String s) {
        if(s == null || s.length() == 0){
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            char tmp = s.charAt(i);
            //left sign
            if(tmp == '(' || tmp == '{' || tmp == '['){
                stack.offerFirst(tmp);
            }else{
                if(stack.isEmpty() || !stack.peekFirst().equals(findMatch(tmp))){
                    return false;
                }
                stack.pollFirst();
            }
        }
        return stack.isEmpty() ? true: false;
    }

    //give right , return left
    private Character findMatch(char tmp){
        if(tmp == '}'){
            return '{';
        }
        if(tmp == ')'){
            return '(';
        }
        if(tmp == ']'){
            return '[';
        }
        else{
            return null;
        }
    }
}
