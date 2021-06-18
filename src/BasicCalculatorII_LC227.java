import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculatorII_LC227 {
    /*

  + - * / : 结算的地方

  人为在式子前和式子后加一个符号。方便结算。



结算顺序                       1   2   3
                          + 3 - 5 + 6 +

              curOp   preOp    preMono       mono

  init                  +         0            0
  第n次结算
   1           -        +         0            3
   2           +        -         3           -5
   3           +        +         -2           6

   res = preMono + mono = -2 + 6 = 4

 结算顺序                      1   2   3
                          + 3 - 5 * 6 +

              curOp   preOp    preMono       mono

  init                  +         0            0
  第n次结算
   1           -        +         0            3
   2           *        -         3           -5
   3           +        *         3           -30

   res = preMono + mono = 3 - 30  =-27



  preMono: the part before the monomial

  mono： last monomial


  */
    //O(n) O(n) https://happygirlzt.com/code/224.html
    public int calculate(String s) {
        char[] charAry = s.toCharArray();
        Deque<Character> queue = new LinkedList<>();
        for(char c : charAry){
            if(c == ' ') continue;
            queue.offerFirst(c);
        }
        queue.offerFirst('+');
        return helper(queue);
    }

    private int helper(Deque<Character> queue){
        char preOp = '+';
        int preMono = 0, mono = 0, num = 0;
        while(!queue.isEmpty()){
            char c = queue.pollLast();
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
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

                preOp = c;
                num = 0;
            }
        }
        return preMono + mono;
    }
}
