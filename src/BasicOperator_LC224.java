import java.util.Deque;
import java.util.LinkedList;

public class BasicOperator_LC224 {
    //O(n) O(n) see 227 for detailed explanation
    public int calculate(String s) {
        if(s == null) return 0;
        char[] charAry = s.toCharArray();
        Deque<Character> queue = new LinkedList<>();
        for(char c : charAry){
            if(c == ' ') continue;
            queue.offerFirst(c);
        }
        queue.offerFirst('+');
        return dfs(queue);

    }

    private int dfs(Deque<Character> queue){
        char preOp = '+';
        int preMono = 0, mono = 0, num = 0;
        while(!queue.isEmpty()){
            char c = queue.pollLast();
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            }else if(c == '('){
                num = dfs(queue);
            }else{
                switch(preOp){
                    case('+'):
                        preMono += mono;
                        mono = num;
                        break;
                    case('-'):
                        preMono += mono;
                        mono = -num;
                        break;
                    case('*'):
                        mono *= num;
                        break;
                    case('/'):
                        mono /= num;
                        break;
                }
                if(c == ')') break;
                preOp = c;
                num = 0;
            }
        }

        return preMono + mono;
    }
}
