import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculatorIII_LC772 {
    //O(n), O(n) https://happygirlzt.com/code/224.html see 227 for detail explanation
    public int calculate(String s) {
        char[] charAry = s.toCharArray();
        Deque<Character> queue = new LinkedList<>();
        for(char c : charAry){
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
