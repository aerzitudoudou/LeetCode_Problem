package amazonOA;

import java.util.Deque;
import java.util.LinkedList;
//leetcode 682
public class CalPoints {
    //T: O(n) S: O(n) n is the length of input ops array
    public int calPoints(String[] ops) {
        if(ops == null || ops.length == 0){
            throw new IllegalArgumentException("Invalid input");
        }
        Deque<Integer> stack = new LinkedList<>();
        int res = 0;
        for(String str : ops){
            if(str.equals("+") && stack.size() >= 2){
                int a = stack.pollFirst();
                int b = stack.pollFirst();
                res += (a + b);
                stack.offerFirst(b);
                stack.offerFirst(a);
                stack.offerFirst(a + b);
            }else if(str.equals("D") && stack.size() >= 1){
                int a = stack.pollFirst();
                res += a * 2;
                stack.offerFirst(a);
                stack.offerFirst(a * 2);
            }else if(str.equals("C") && stack.size() >= 1){
                int a = stack.pollFirst();
                res -= a;
            }else{
                try{
                    int a = Integer.parseInt(str);
                    stack.offerFirst(a);
                    res += a;
                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("Invalid input");
                }
            }
        }
        return res;
    }


    /*amazon version
Several teams across Amazon are participating in a company-wide robotics challenge. Your team has programmed a robot to play a game in which it throws a ball at various blocks marked with a symbol so as to knock these out. You hav ebeen asked to automate the scoring process. A score is computed for each throw. The "last score" is the score of the previous throw (or 0, if there is no previous throw) and th etotal score is the sum of the scores of all the throws. the symbol on a block can be an integer, a sign or a letter. Each sign or letter represents a special rule as given below:

If a throw hits a block with an integer, the score for that throw is the value of that integer.
If a throw hits a block with an X, the score for that throw is double the last score.
If a throw hits a block with +, the score for that throw is the sum of the last two scores.
If a throw hits a block with a Z, the last score is removed, as though the last throw never happened. Its value does not count towards the total score, and the subsequent throws will ignore it when computing their values.

Write an algorithm that computes the total score for a given list of ordered hits by the robot.

Input
The input to the function/method consists of two arguments -
num, an integer representing the number of symbols in the list;
blocks, the list of strings representing symbols of the list;

Output
Return an integer representing the total score for the given list of ordered hits.

Examples

Input:
num = 8
blocks = [5, -2, 4, Z, X, 9, +, +]

Expected Return Value: 27

Input:
num = 4
blocks = [1, 2, +, Z]

Expected Return Value: 3

    * */

    //T: O(n) S: O(n) n is the length of input ops array
    public int calPoints2(String[] ops) {
        if(ops == null || ops.length == 0){
            throw new IllegalArgumentException("Invalid input");
        }
        Deque<Integer> stack = new LinkedList<>();
        int res = 0;
        for(String str : ops){
            if(str.equals("+") && stack.size() >= 2){
                int a = stack.pollFirst();
                int b = stack.pollFirst();
                res += (a + b);
                stack.offerFirst(b);
                stack.offerFirst(a);
                stack.offerFirst(a + b);
            }else if(str.equals("X") && stack.size() >= 1){
                int a = stack.pollFirst();
                res += a * 2;
                stack.offerFirst(a);
                stack.offerFirst(a * 2);
            }else if(str.equals("Z") && stack.size() >= 1){
                int a = stack.pollFirst();
                res -= a;
            }else{
                try{
                    int a = Integer.parseInt(str);
                    stack.offerFirst(a);
                    res += a;
                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("Invalid input");
                }
            }
        }
        return res;
    }



    //Best solution as for the coding style
    //Stack implement Collection interface therefore can use for each loop
    public int calPoints3(String[] ops) {
        //corner cases
        if(ops == null || ops.length == 0){
            throw new IllegalArgumentException("Invalid input.");
        }
        Deque<Integer> stack = new LinkedList<>();
        int res = 0;
        for(String str : ops){
            if(str.equals("+") && stack.size() >= 2){
                int top = stack.pollFirst();
                int newTop = top + stack.peekFirst();
                stack.offerFirst(top);
                stack.offerFirst(newTop);
            }else if(str.equals("D") && stack.size() >= 1){
                stack.offerFirst(stack.peekFirst() * 2);
            }else if(str.equals("C") && stack.size() >= 1){
                stack.pollFirst();
            }else{
                try{
                    stack.offerFirst(Integer.parseInt(str));
                }catch(NumberFormatException ex){
                    throw new IllegalArgumentException("Invalid input.");
                }
            }
        }
        for(Integer i : stack){
            res += i;
        }
        return res;
    }



}
