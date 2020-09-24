import java.util.ArrayDeque;
import java.util.Queue;

/*
*
* Leetcode
* 772. Basic Calculator III
Hard

Share
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12


Note: Do not use the eval built-in library function.
*
* */
/*

   (23 + (4 - 2)) * 7

           (
                   23 + (4 - 2)) * 7

                           4 - 2)) * 7

           sum             prev               prevOp              num              c
           0                0                   +                  0
           0                0                   +                  4               -
           0                4                   -                  0
           0                4                   -                  2
           4                -2                                                     )

           sum = prev(+, -)
           prev = num

           prev = prev (*or /) num


           prev ? num

                                       3 + 4 * 5
           sum             prev               prevOp              num              c
           0                0                   +                  0
           0                0                   +                  3               +
           0                +3                  +                  0
           0                +3                  +                  4               *
           +3               +4                  *                  0
           +3               +4                  *                  5               ''
           +3               20                  ''                 0

           23



数字           --> 直接update当前数字


           +
           -
           *
           /
           )
           啥都没有情况




           (  --> dfs







*/


public class BasicCalculatorIII {
    public int calculate(String s) {
        Queue<Character> q = new ArrayDeque<>();
        for(char c : s.toCharArray()){
            if(c != ' '){
                q.offer(c);
            }
        }

        q.offer(' ');
        return helper(q);
    }

    private int helper(Queue<Character> q){
        int num = 0, prev = 0, sum = 0;
        char prevOp = '+';

        while(!q.isEmpty()){
            char c = q.poll();
            if(c >= '0' && c <= '9'){ //字母
                num = num * 10 + c - '0';
            }else if(c == '('){
                num = helper(q);
            }else{//符号|| ')'
                switch(prevOp){
                    case '+':
                        sum += prev;
                        prev = +num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;

                }
                if(c == ')') break;
                prevOp = c;
                num = 0;
            }

        }
        return sum + prev;
    }
}
